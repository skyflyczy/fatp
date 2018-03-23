package com.telecwin.fatp.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.encrypt.SymmetricEncrypt;
import com.huajin.baymax.service.AbstractBaseService;
import com.telecwin.fatp.po.sys.SysParamPo;
import com.telecwin.fatp.service.datasupprot.sys.SysParamDataSupportService;

/**
 * SysParam
 */
@Service
public class SysParamService extends AbstractBaseService {
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
}
