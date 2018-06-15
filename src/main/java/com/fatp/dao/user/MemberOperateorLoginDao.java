package com.fatp.dao.user;

import java.util.Map;

import com.fatp.po.user.MemberOperateorLoginPo;
import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;

/**
 * 
 * MemberOperateorLogin
 * @author auto-generator
 * 2015-08-03 30:10:46
 */
@MyBatisDao
public interface MemberOperateorLoginDao {
	public MemberOperateorLoginPo getLoginRecord(Map<String,Object> map);
	@NoRowAffectException
	public int update(MemberOperateorLoginPo entity);
	public int insert(MemberOperateorLoginPo entity);
}
