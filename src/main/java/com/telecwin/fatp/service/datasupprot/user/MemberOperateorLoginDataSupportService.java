package com.telecwin.fatp.service.datasupprot.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.user.MemberOperateorLoginDao;
import com.telecwin.fatp.po.user.MemberOperateorLoginPo;

@Service
public class MemberOperateorLoginDataSupportService {
	@Autowired
	private MemberOperateorLoginDao memberOperateorLoginDao;
	
	/**
	 * 获取登录记录
	 * @param loginName
	 * @param appId
	 * @return
	 * @return MemberOperateorLogin
	 * @author zhiya.chai
	 * 2015年8月3日 下午3:25:05
	 */
	public MemberOperateorLoginPo getLoginRecord(String loginName,Integer appId,Integer memberId,int exchangeId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginName", loginName);
		map.put("appId", appId);
		map.put("memberId", memberId);
		map.put("exchangeId", exchangeId);
		return memberOperateorLoginDao.getLoginRecord(map);
	}
	/**
	 * 新增登录记录
	 * @param content
	 * @return
	 * @return Response
	 * @author zhiya.chai
	 */
	public int insert(MemberOperateorLoginPo memberOperateorLogin){
		return memberOperateorLoginDao.insert(memberOperateorLogin);
	}
	/**
	 * 更新登录记录
	 * @param content
	 * @return
	 * @return Response
	 * @author zhiya.chai
	 */
	public int update(MemberOperateorLoginPo memberOperateorLogin){
		return memberOperateorLoginDao.update(memberOperateorLogin);
	}
	
}
