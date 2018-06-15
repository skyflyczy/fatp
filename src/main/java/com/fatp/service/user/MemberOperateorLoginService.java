package com.fatp.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.enums.user.LoginType;
import com.fatp.po.user.MemberOperateorLoginPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.user.MemberOperateorLoginDataSupportService;
import com.fatp.util.Constant;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;

/**
 * 用户登录
 * @author zhiya.chai
 */
@Service
public class MemberOperateorLoginService extends BaseService{
	
	@Autowired
	private MemberOperateorLoginDataSupportService memberOperateorLoginDataSupportService;
	
	/**
	 * 获取登录用户信息
	 * @param loginName
	 * @param memberId
	 * @return
	 */
	public MemberOperateorLoginPo getLoginRecord(String loginName,int memberId,int exchangeId) {
		return memberOperateorLoginDataSupportService.getLoginRecord(loginName, Constant.SYSAPPID, memberId,exchangeId);
	}

	/**
	 * 创建新登录记录
	 * @param loginName
	 * @return
	 */
	public MemberOperateorLoginPo createNewLoginRecord(String loginName,int exchangeId){
		MemberOperateorLoginPo loginRecord = new MemberOperateorLoginPo();
		loginRecord.setLoginName(loginName);
		loginRecord.setLoginType(LoginType.用户名.type);
		loginRecord.setAppId(Constant.SYSAPPID);
		loginRecord.setFailNum(0);
		loginRecord.setSuccessNum(0);
		loginRecord.setFailNumLx(0);
		loginRecord.setSuccessNumLx(0);
		loginRecord.setExchangeId(exchangeId);
		return loginRecord;
	}
	/**
	 * 保存登录信息
	 * @param loginRecord
	 * @return
	 */
	public void saveLoginRecord(MemberOperateorLoginPo loginRecord){
		try {
			if(loginRecord.getId() == null) {
				memberOperateorLoginDataSupportService.insert(loginRecord);
			} else {
				memberOperateorLoginDataSupportService.update(loginRecord);
			}
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "saveLoginRecord", e));
		}
	}
}
