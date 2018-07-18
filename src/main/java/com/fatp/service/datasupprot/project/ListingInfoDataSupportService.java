package com.fatp.service.datasupprot.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.fatp.util.BigDecimalUtil;
import com.fatp.util.StringUtil;
import com.fatp.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.encrypt.SymmetricEncrypt;

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
		decryptStr(list);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据条件查找挂牌信息
	 * @param map
	 * @return
	 */
	public List<ListingInfo> findByCondition(Map<String,Object> map) {
		List<ListingInfo> list = listingInfoDao.findByCondition(map);
		decryptStr(list);
		return list;
	}
	/**
	 * 根据Guid查找挂牌信息
	 * @param listingGuid
	 * @return
	 */
	public ListingInfo getByListingGuid(String listingGuid) {
		ListingInfo listingInfo = listingInfoDao.getByListingGuid(listingGuid);
		decryptStr(listingInfo);
		return listingInfo;
	}
	/**
	 * 根据Id查找挂牌信息
	 * @param id
	 * @return
	 */
	public ListingInfo getLisingInfoById(Integer id) {
		ListingInfo listingInfo = listingInfoDao.getLisingInfoById(id);
		decryptStr(listingInfo);
		return listingInfo;
	}
	/**
	 * 根据Id获取挂牌信息
	 * @param id
	 * @return
	 */
	public ListingInfoPo getLisingInfoPoById(Integer id) {
		ListingInfoPo listingInfoPo = listingInfoDao.getPoById(id);
		decryptStr(listingInfoPo);
		return listingInfoPo;
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
		encryptStr(listingInfoPo);//加密
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
		encryptStr(newPo);//加密
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
	/**
	 * 敏感信息加密
	 * @param listingInfoPo
	 */
	private void encryptStr(ListingInfoPo listingInfoPo){
		if(StringUtils.isNotBlank(listingInfoPo.getSettleAccountName())) {
			listingInfoPo.setSettleAccountName(SymmetricEncrypt.encryptStr(listingInfoPo.getSettleAccountName()));
		}
		if(StringUtils.isNotBlank(listingInfoPo.getSettleCardAccount())) {
			listingInfoPo.setSettleCardAccount(SymmetricEncrypt.encryptStr(listingInfoPo.getSettleCardAccount()));
		}
	}
	
	/**
	 * 解密
	 * @param list
	 */
	private void decryptStr(List<ListingInfo> list){
		if(CollectionUtils.isNotEmpty(list)) {
			//解密
			list.stream().forEach(detail ->{
				if(StringUtils.isNotBlank(detail.getSettleAccountName())) {
					detail.setSettleAccountName(SymmetricEncrypt.decryptStr(detail.getSettleAccountName()));
				}
				if(StringUtils.isNotBlank(detail.getSettleCardAccount())) {
					detail.setSettleCardAccount(SymmetricEncrypt.decryptStr(detail.getSettleCardAccount()));
				}
			});
		}
	}
	/**
	 * 解密
	 * @param list
	 */
	private void decryptStr(ListingInfo listingInfo){
		//解密
		if(StringUtils.isNotBlank(listingInfo.getSettleAccountName())) {
			listingInfo.setSettleAccountName(SymmetricEncrypt.decryptStr(listingInfo.getSettleAccountName()));
		}
		if(StringUtils.isNotBlank(listingInfo.getSettleCardAccount())) {
			listingInfo.setSettleCardAccount(SymmetricEncrypt.decryptStr(listingInfo.getSettleCardAccount()));
		}
	}
	/**
	 * 解密
	 * @param list
	 */
	private void decryptStr(ListingInfoPo listingInfoPo){
		//解密
		if(StringUtils.isNotBlank(listingInfoPo.getSettleAccountName())) {
			listingInfoPo.setSettleAccountName(SymmetricEncrypt.decryptStr(listingInfoPo.getSettleAccountName()));
		}
		if(StringUtils.isNotBlank(listingInfoPo.getSettleCardAccount())) {
			listingInfoPo.setSettleCardAccount(SymmetricEncrypt.decryptStr(listingInfoPo.getSettleCardAccount()));
		}
	}

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public String listingRecords(List<ListingInfoPo> poilist) throws Exception {
		int successNum = 0;
		int failNum=0;
		String errBizCode = "";
		String importResult="";
		SqlSession sqlSession=null;
		try {
			sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
			ListingInfoDao liDao = sqlSession.getMapper(ListingInfoDao.class);
			for (ListingInfoPo po : poilist) {
				try {
					List<ListingTradePo> tradePo = getListingTradePo(po);
					for (ListingTradePo tp : tradePo) {
						listingTradeDao.insert(tp);// 插入listing_trade表
					}
					liDao.insert(po);// 插入listing_info表
					successNum ++;
				} catch (Exception e) {
					e.printStackTrace();
					failNum++;
					errBizCode += po.getPartnerBizCode() + ";";
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		} finally {
			if(sqlSession!=null)sqlSession.close();		
		}

		if (errBizCode.equals("")) {
			importResult = "成功导入[ " + successNum + " ]条";
		} else {
			importResult = "成功导入[ " + successNum + " ]条； 失败["+failNum+"]条，产品编号【" + errBizCode + "】。";
		}
		System.out.println("----excel导入结果信息="+importResult);
		return importResult;
	}

	/**
 * 把导入文件中【预期收益率值（%）】的值解析为ListingTradePo，插入到listing_trade表中
 * @param po ListingTradePo
 * @return
 */
	private List<ListingTradePo> getListingTradePo(ListingInfoPo po) {
		String pValue = po.getProfitValue();
		System.out.println("---------ListingInfoPo="+po);
		List<ListingTradePo> plist = new ArrayList<ListingTradePo>();
		if (StringUtil.isBlank(pValue)) {
			return plist;
		}
		String[] pv = pValue.split(",");
		BigDecimal minInvestMoney = po.getMinInvestMoney() == null ? BigDecimal.ZERO : po.getMinInvestMoney();
		for (int i =0;i<pv.length;i++) {
			String profit = pv[i];
			System.out.println("---------"+i+"----------profit="+profit);
			if (profit != null && !profit.equals("")) {
				String[] minToMax = profit.split(":");
				ListingTradePo tradePo = new ListingTradePo();
				tradePo.setListingInfoId(po.getId());// 挂牌产品Id,listing_info.id
				BigDecimal bd = new BigDecimal(minToMax[1]);
				BigDecimal bd2 = new BigDecimal("0.01");
				tradePo.setInvestProfit(bd.multiply(bd2));// 拟定年化收益率,没有乘以%InvestProfit
				tradePo.setMaxInvestMoney(BigDecimalUtil.convertDefaultZero(minToMax[0]));// 最高投资金额，元（不包含此值）
				tradePo.setMinInvestMoney(minInvestMoney);
				tradePo.setCreateOperatorId(po.getCreateOperatorId());
				tradePo.setUpateOperatorId(po.getUpateOperatorId());
				plist.add(tradePo);
				minInvestMoney = tradePo.getMaxInvestMoney();
				System.out.println("---------"+i+"----------tradePo--------"+tradePo);
			}
		}
		System.out.println("-------------------plist--------"+plist);
		return plist;
	}	
}
