package com.fatp.dao.user;

import java.util.List;
import java.util.Map;

import com.fatp.po.user.UcUserPubinfoPo;
import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;

/**
 * UcUserPubinfo
 */
@MyBatisDao
public interface UcUserPubinfoDao {
	
	public UcUserPubinfoPo getByCompanyOrgCode(String companyOrgCode);
	public UcUserPubinfoPo getByCompanyNameEqual(String companyNameEqual);
	public UcUserPubinfoPo getById(Integer id);
	
	public List<UcUserPubinfoPo> select(Map<String, Object> map);
	
	public void insertPub(UcUserPubinfoPo o);
	
	@NoRowAffectException
	public int updatePub(UcUserPubinfoPo o);
}
