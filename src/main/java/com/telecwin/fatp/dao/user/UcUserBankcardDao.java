package com.telecwin.fatp.dao.user;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.domain.UcUserBankcard;

/**
 * UcUserBankcard	
 */
@MyBatisDao
public interface UcUserBankcardDao {
	
	public UcUserBankcard getById(Map<String, Object> map);
	
	public UcUserBankcard getByCardId(int id);
	
	public List<UcUserBankcard> select(Map<String, Object> map);
	
	public int insert(UcUserBankcard o);
	
	@NoRowAffectException
	public int update(UcUserBankcard o);
	
	@NoRowAffectException
	public int updateCardStatus(UcUserBankcard o);
	
	public int updateCardStatusByUserId(Map<String, Object> map);
	
	public UcUserBankcard getDefaultCard(int userId);
	
	@NoRowAffectException
	public void delete(int id);
}
