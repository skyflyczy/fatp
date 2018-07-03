package com.fatp.service.datasupprot.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.dao.project.ListingInfoDao;
import com.fatp.dao.project.ListingTradeDao;
import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.listing.ListingTrade;
import com.fatp.enums.project.ListingStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.po.project.ListingTradePo;
import com.fatp.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 挂牌数据支持服务
 * @author zhiya.chai
 * @date 2018年6月12日 下午3:37:53
 */
@Service
public class ListingInfoDataSupportService {

	@Autowired
	private ListingInfoDao listingInfoDao;
	@Autowired
	private ListingTradeDao listingTradeDao;
	/**
	 * 根据条件分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ListingInfo> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize){
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<ListingInfo> list = listingInfoDao.findByCondition(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据条件查找挂牌信息
	 * @param map
	 * @return
	 */
	public List<ListingInfo> findByCondition(Map<String,Object> map) {
		return listingInfoDao.findByCondition(map);
	}
	/**
	 * 根据Guid查找挂牌信息
	 * @param listingGuid
	 * @return
	 */
	public ListingInfo getByListingGuid(String listingGuid) {
		return listingInfoDao.getByListingGuid(listingGuid);
	}
	/**
	 * 根据Id获取挂牌信息
	 * @param id
	 * @return
	 */
	public ListingInfoPo getLisingInfoPoById(Integer id) {
		return listingInfoDao.getPoById(id);
	}
	/**
	 * 根据挂牌Id查找挂牌交易信息
	 * @param listingInfoId
	 * @return
	 */
	public List<ListingTrade> getTradeByListingInfoId(Integer listingInfoId) {
		return listingTradeDao.getTradeByListingInfoId(listingInfoId);
	}
	/**
	 * 添加信息
	 * @param po
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public ListingInfoPo addListingInfo(ListingInfo listingInfoVo){
		ListingInfoPo listingInfoPo = new ListingInfoPo();
		BeanUtils.copyProperties(listingInfoVo, listingInfoPo);
		listingInfoPo.setListingGuid(UUIDUtil.getUUID());
		listingInfoPo.setListingName(listingInfoVo.getListingFullName().trim());
		listingInfoPo.setListingStatus(ListingStatus.正常.status);
		listingInfoDao.insert(listingInfoPo);
		return listingInfoPo;
	}
	/**
	 * 更新挂牌信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public ListingInfoPo updateListingInfo(ListingInfo listingInfoVo) {
		ListingInfoPo listingInfoPo = listingInfoDao.getPoById(listingInfoVo.getId());
		if(listingInfoPo == null) {
			//挂牌信息不存在
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		if(listingInfoPo.getVersionNo().intValue() != listingInfoVo.getVersionNo().intValue()) {
			throw new FatpException(ErrorCode.LISTING_ALREADY_UPDATE);
		}
		ListingInfoPo newPo = new ListingInfoPo();
		BeanUtils.copyProperties(listingInfoVo, newPo);
		newPo.setId(listingInfoVo.getId());
		newPo.setListingName(listingInfoVo.getListingFullName());
		newPo.setUpateOperatorId(listingInfoVo.getUpateOperatorId());
		int row = listingInfoDao.updateByVersion(newPo);
		if(row < 1){
			throw new FatpException(ErrorCode.LISTING_SAVE_ERROR);
		}
		return newPo;
	}
	/**
	 * 更新挂牌交易信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateListingTrade(ListingInfo listingInfoVo) {
		List<ListingTrade> tradeList = listingInfoVo.getListingTradeList();
		if(CollectionUtils.isEmpty(tradeList)) {
			listingTradeDao.deleteByListingInfoId(listingInfoVo.getId());
			return;
		}
		//获取原有交易信息Id集合
		Set<Integer> tradeIdSet = new HashSet<>();
		//更新交易信息
		tradeList.stream().forEach(trade ->{
			ListingTradePo newPo = new ListingTradePo();
			BeanUtils.copyProperties(trade, newPo);
			newPo.setCreateOperatorId(listingInfoVo.getUpateOperatorId());
			newPo.setUpateOperatorId(listingInfoVo.getUpateOperatorId());
			newPo.setListingInfoId(listingInfoVo.getId());
			newPo.setId(trade.getId());
			newPo.setInvestProfit(newPo.getInvestProfit().divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP));
			if(trade.getId() == null) {
				//新插入一条记录
				listingTradeDao.insert(newPo);
			} else {
				//更新记录
				listingTradeDao.update(newPo);
			}
			tradeIdSet.add(newPo.getId());
		});
		//获取原有的，要删除哪些去掉的交易信息
		List<ListingTradePo> tradePoList = listingTradeDao.getPoByListingInfoId(listingInfoVo.getId());
		tradePoList.stream().forEach(po ->{
			//如果不包含更新的，删除掉
			if(!tradeIdSet.contains(po.getId())) {
				listingTradeDao.deleteById(po.getId());
			}
		});
	}
	/**
	 * 删除挂牌信息
	 * @param id
	 * @param versionNo
	 */
	public void deleteListingInfo(int id,int versionNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("versionNo", versionNo);
		map.put("listingStatus", ListingStatus.删除.status);
		int n = listingInfoDao.delete(map);
		if(n < 1) {
			throw new FatpException(ErrorCode.LISTING_DELELT_ERROR);
		}
	}
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	public int listingRecords(List<ListingInfoPo> poilist) throws Exception {  
		int result =0;
	    SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);  
	    ListingInfoDao liDao = sqlSession.getMapper(ListingInfoDao.class);  
	    try {  
			for (ListingInfoPo po : poilist) {
				result += liDao.insert(po);//插入listing_info表
				List<ListingTradePo> tradePo = getListingTradePo(po);
				for (ListingTradePo tp : tradePo) {
					System.out.println("--------插入listing_trade表"+tp);
					listingTradeDao.insert(tp);//插入listing_trade表
				}
	        }  
	        sqlSession.commit();  
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw e;
	    }finally {  
	        sqlSession.close();  
	    }  
	    return result;
	}
/**
 * 把导入文件中【预期收益率值（%）】的值解析为ListingTradePo，插入到listing_trade表中
 * @param po ListingTradePo
 * @return
 */
	private List<ListingTradePo> getListingTradePo(ListingInfoPo po) {
		String pValue = po.getProfitValue();
		List<ListingTradePo> plist = new ArrayList<ListingTradePo>();
		if (pValue != null && !pValue.equals("")) {
			String[] pv = pValue.split(",");
			for (int i =0;i<pv.length;i++) {
				String profit = pv[i];
				System.out.println(i+"-------------------profit="+profit);
				if (profit != null && !profit.equals("")) {
					String[] minToMax = profit.split(":");

					ListingTradePo tradePo = new ListingTradePo();
					tradePo.setId(po.getId()+i);
					tradePo.setListingInfoId(po.getId());// 挂牌产品Id,listing_info.id
					BigDecimal bd = new BigDecimal(minToMax[1]);
					BigDecimal bd2 = new BigDecimal("0.01");
					tradePo.setInvestProfit(bd.multiply(bd2));// 拟定年化收益率,没有乘以%InvestProfit
					tradePo.setMaxInvestMoney(new BigDecimal(minToMax[0]));// 最高投资金额，元（不包含此值）
					tradePo.setCreateOperatorId(1);
					plist.add(tradePo);
				}
			}
		}
		System.out.println("-------------------plist--------"+plist);
		return plist;
	}	
}
