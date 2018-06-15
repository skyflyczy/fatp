package com.fatp.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SysParamPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SysParamDataSupportService;
import com.huajin.baymax.encrypt.SymmetricEncrypt;

/**
 * SysParam
 */
@Service
public class SysParamService extends BaseService {
	@Autowired
	private SysParamDataSupportService sysParamDataSupportService;
	
	public List<SysParamPo> select(Map<String, Object> map) {
		return sysParamDataSupportService.select(map);
	}
	/**
	 * 登录失败次数
	 * @return
	 */
	public int loginFrozenFailNum() {
		SysParamPo sysParam = sysParamDataSupportService.getByParamKey("Sys_Login_Frozen_FailNum");
		return Integer.parseInt(sysParam.getParamValue());
	}
	/**
	 * 登录冻结时间
	 * @return
	 */
	public int loginFrozenMinute() {
		SysParamPo sysParam = sysParamDataSupportService.getByParamKey("Sys_Login_Frozen_Minute");
		return Integer.parseInt(sysParam.getParamValue());
	}
	/**
	 * 获取系统密码
	 * @return
	 */
	public String getSysDefaultPwd() {
		SysParamPo sysParam = sysParamDataSupportService.getByParamKey("Sys_Default_PWD");
		return SymmetricEncrypt.decryptStr(sysParam.getParamValue());
	}
	/**
	 * 项目文件上传地址
	 * @return
	 */
	public String getProjectUploadAddress() {
		SysParamPo sysParam = sysParamDataSupportService.getByParamKey("SYS_PROJECT_FILE_BASE");
		return sysParam.getParamValue();
	}
}
