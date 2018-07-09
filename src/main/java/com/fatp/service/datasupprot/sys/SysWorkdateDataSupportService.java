package com.fatp.service.datasupprot.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysWorkdateDao;
import com.fatp.enums.YesNo;
import com.fatp.exception.FatpException;
import com.fatp.po.sys.SysWorkdatePo;
import com.fatp.util.DateUtil;
import com.huajin.baymax.util.DateUtils;
/**
 * 工作日支持类
 * 
 * @author zhiya.chai
 * @date 2018年6月23日 下午3:39:08
 */
@Service
public class SysWorkdateDataSupportService {

	@Autowired
	private SysWorkdateDao sysWorkdateDao;
	/**
	 * 是否是工作日
	 * @param date
	 * @return
	 */
	public boolean isWorkDate(Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workDate", DateUtil.formatDate(date, "yyyy-MM-dd"));
		map.put("isWorked", YesNo.是.value);
		List<SysWorkdatePo> list = sysWorkdateDao.select(map);
		return CollectionUtils.isNotEmpty(list);
	}
	/**
	 * 获得日期所属工作日
	 * @param date
	 * @return
	 */
	public Date getBelongWorkDate(Date date){
		if(date == null) {
			throw new FatpException("获取工作日异常，日期为空。");
		}
		boolean isWorkDate = isWorkDate(date);
		if(isWorkDate) {
			return date;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("workDate", DateUtil.formatDate(date, "yyyy-MM-dd"));
		SysWorkdatePo workDate = sysWorkdateDao.getBelongWorkDate(map);
		if(workDate == null) {
			return null;
		} else {
			return workDate.getWorkDate();
		}
	}
	/**
	 * 获取N天前的工作日
	 * @param date
	 * @param days
	 * @return
	 */
	public Date getBeforeWorkDate(Date date,int days){
		days = days < 0 ? 0 : days;
		boolean isWorkDate = isWorkDate(date);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("workDate", DateUtils.formatDate(date, "yyyy-MM-dd"));
		if(isWorkDate){
			//当前日期属于工作日
			map.put("start", days);
		}else{
			//当前日期不属于工作日
			map.put("start", days-1);
		}
		SysWorkdatePo workDate = sysWorkdateDao.getBeforeWorkDate(map);
		return workDate == null ? null : workDate.getWorkDate();
	}
}
