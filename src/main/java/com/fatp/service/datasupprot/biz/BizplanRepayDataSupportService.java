package com.fatp.service.datasupprot.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.biz.BizplanRepayDao;
import com.fatp.domain.PageData;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.exception.FatpException;
import com.fatp.po.biz.BizplanRepayPo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 还款计划
 * @author zhiya.chai
 * @date 2018年6月23日 下午6:00:14
 */
@Service
public class BizplanRepayDataSupportService {

	@Autowired
	private BizplanRepayDao bizplanRepayDao;
	/**
	 * 分页查找还款计划
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<BizplanRepay> pageFindRepayList(Map<String,Object> map,int pageNo, int pageSize){
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<BizplanRepay> list = bizplanRepayDao.select(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	
	/**
	 * 插入还款计划
	 * @param o
	 * @return
	 */
	public int insert(BizplanRepayPo o) {
		return bizplanRepayDao.insert(o);
	}
	/**
	 * 根据条件查找还款计划
	 * @param map
	 * @return
	 */
	public List<BizplanRepay> findRepayPlanByCondition(Map<String, Object> map) {
		return bizplanRepayDao.select(map);
	}
	/**
	 * 更新删除状态
	 * @param map
	 * @return
	 */
	public void updateDeletStatusByApply(Map<String,Object> map) {
		int n = bizplanRepayDao.updateDeletStatusByApply(map);
		if(n <= 0) {
			throw new FatpException("更新还款计划失败");
		}
	}
	/**
	 * 根据Guid获取还款计划
	 * @param repayPlanGuid
	 * @return
	 */
	public BizplanRepay getPlanRepayByGuid(String repayPlanGuid){
		return bizplanRepayDao.getPlanRepayByGuid(repayPlanGuid);
	}
	/**
	 * 更新还款状态
	 * @param po
	 */
	public void updateRepayStatus(BizplanRepayPo po) {
		int n = bizplanRepayDao.updateRepayStatus(po);
		if(n <= 0) {
			throw new FatpException("更新还款状态失败");
		}
	}
}
