package com.telecwin.fatp.dao.user;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.po.user.MemberOperatorPo;

/**
 * 
 * MemberOperator
 * @author auto-generator
 */
@MyBatisDao
public interface MemberOperatorDao {
	
	public int insert(MemberOperatorPo entity);
	@NoRowAffectException
	public int update(MemberOperatorPo entity);
	
	public MemberOperatorPo getById(Map<String, Object> map);
	
	/**
	 * 根据一些条件查询，如登录名，会员id，手机号，身份证，email等
	 * @param map
	 * @return
	 */
	public List<MemberOperatorPo> select(Map<String, Object> map);
	/**
	 * 获取超级管理员
	 * @param map
	 * @return
	 * @return MemberOperatorPo
	 */
	public MemberOperatorPo getSuperAdmin(Map<String,Object> map);
	
	public void deleteById(Map<String,Object> map);
	
}
