package com.telecwin.fatp.service.datasupprot.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.telecwin.fatp.dao.project.ProjectRecordDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.project.ProjectRecordComplex;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;
import com.telecwin.fatp.util.UUIDUtil;

@Service
public class ProjectRecordDataSupportService {
	
	@Autowired
	private ProjectRecordDao projectRecordDao;

	/**
	 * 根据条件分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ProjectRecordComplex> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize){
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<ProjectRecordComplex> list = projectRecordDao.findByCondition(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 新增备案
	 * @param recordinfoPo
	 */
	public void addRecord(ProjectRecordinfoPo recordinfoPo) {
		recordinfoPo.setRecordFullName(recordinfoPo.getRecordFullName().trim());
		//TODO 暂定为1
		recordinfoPo.setOriginType(0);
		recordinfoPo.setProjectUnitPrice(BigDecimal.ONE);
		recordinfoPo.setRecordStatus(RecordStatusDesc.待提交.value);
		if(recordinfoPo.getProjectMoney() == null) {
			recordinfoPo.setProjectMoney(BigDecimal.ZERO);
		}
		recordinfoPo.setRecordGuid(UUIDUtil.getUUID());
		projectRecordDao.insert(recordinfoPo);
	}
	
	public ProjectRecordinfo getById(int id) {
		return projectRecordDao.getById(id);
	}
	
	public ProjectRecordinfo getByRecordGuid(String recordGuid){
		return projectRecordDao.getByRecordGuid(recordGuid);
	}
	
	 /**
     * 更新备案信息
     * @param recordInfoPo
     * @return
     */
	public void update(ProjectRecordinfoPo recordInfoPo) {
		recordInfoPo.setUpdateTime(new Date());
		int result = projectRecordDao.update(recordInfoPo);
		if(result <= 0) {
			throw new FatpException(ErrorCode.SYSTEM_ERROR);
		}
	}
	/**
	 * 删除备案信息
	 * @param id
	 * @param versionNo
	 */
	public void deleteRecordById(Integer id,Integer versionNo) {
		if(id == null || versionNo == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("versionNo", versionNo);
		projectRecordDao.delete(map);
	}
}
