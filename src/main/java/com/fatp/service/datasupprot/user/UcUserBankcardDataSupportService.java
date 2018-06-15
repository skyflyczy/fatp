package com.fatp.service.datasupprot.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.user.UcUserBankcardDao;
import com.fatp.domain.UcUserBankcard;
import com.fatp.enums.user.CardStatusDesc;
import com.huajin.baymax.encrypt.SymmetricEncrypt;

@Service
public class UcUserBankcardDataSupportService {

	@Autowired
	private UcUserBankcardDao ucUserBankcardDao;
	/**
	 * 获取用户银行卡信息
	 * @param userId
	 * @param exchangeId
	 * @return
	 */
	public List<UcUserBankcard> getUserBankcards(int userId,int exchangeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("cardStatus", CardStatusDesc.正常.value);
		map.put("exchangeId", exchangeId);
		List<UcUserBankcard> list = ucUserBankcardDao.select(map);
		if(CollectionUtils.isNotEmpty(list)) {
			for(UcUserBankcard bankcard : list) {
				doDcryptBankCard(bankcard);
			}
		}
		return list;
	}
	/**
	 * 获取用户的某张卡
	 * @param userId
	 * @param id
	 * @return
	 */
	public UcUserBankcard getUserBankcardById(Integer userId,Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("id", id);
		UcUserBankcard bankcard = ucUserBankcardDao.getById(map);
		if(bankcard != null) {
			doDcryptBankCard(bankcard);
		}
		return bankcard;
	}
	/**
	 * 获取用户正常卡
	 * @param userId
	 * @return
	 */
	public List<UcUserBankcard> getUserCanUseBankCard(int userId){
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("userId", userId);
		map.put("cardStatus", CardStatusDesc.正常.value);
		List<UcUserBankcard> list = ucUserBankcardDao.select(map);
		if(CollectionUtils.isNotEmpty(list)) {
			for(UcUserBankcard bankcard : list) {
				doDcryptBankCard(bankcard);
			}
		}
		return list;
	}
	/**
	 * 获取默认卡
	 * @return UcUserBankcard
	 * @author zhiya.chai
	 * 2016年6月20日 上午11:01:31
	 */
	public UcUserBankcard getDefaultCard(Integer userId) {
		UcUserBankcard o = ucUserBankcardDao.getDefaultCard(userId);
		if(o != null){
			doDcryptBankCard(o);
		}
		return o;
	}
	/**
	 * 根据银行卡号获取银行卡信息
	 * @param userId
	 * @param cardAccount
	 * @return
	 */
	public List<UcUserBankcard> getUserBankCardByCardAccount(int userId,String cardAccount){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("cardAccount", SymmetricEncrypt.encryptStr(cardAccount));
		List<UcUserBankcard> list = ucUserBankcardDao.select(map);
		if(CollectionUtils.isNotEmpty(list)) {
			for(UcUserBankcard bankcard : list) {
				doDcryptBankCard(bankcard);
			}
		}
		return list;
	}
	
	/**
	 * 新增
	 * @return int
	 */
	public int insert(UcUserBankcard o) {
		//加密
		doEncryptBankCard(o);
		return ucUserBankcardDao.insert(o);
	}
	
	/**
	 * 更新 
	 * @return int
	 */
	public int update(UcUserBankcard o) {
		if(o != null){
			doEncryptBankCard(o);
		}
		return ucUserBankcardDao.update(o);
	}
	
	/**
	 * 更新卡状态
	 * @return int
	 * @author zhiya.chai
	 * 2016年6月20日 上午11:02:40
	 */
	public int updateCardStatus(UcUserBankcard card) {
		if(card != null){
			doEncryptBankCard(card);
		}
		return ucUserBankcardDao.updateCardStatus(card);
	}
	
	/**
	 * 加密
	 * @return void
	 */
	private void doEncryptBankCard(UcUserBankcard bankcard) {
		//cardAccount
		String cardAccount = bankcard.getCardAccount();
		cardAccount = StringUtils.isNotBlank(cardAccount) ? SymmetricEncrypt.encryptStr(cardAccount) : cardAccount;
		bankcard.setCardAccount(cardAccount);
		//accountName
		String accountName = bankcard.getAccountName();
		accountName = StringUtils.isNotBlank(accountName) ? SymmetricEncrypt.encryptStr(accountName) : accountName;
		bankcard.setAccountName(accountName);
		//phoneInBank 
		String phoneInBank = bankcard.getPhoneInBank();
		phoneInBank = StringUtils.isNotBlank(phoneInBank) ? SymmetricEncrypt.encryptStr(phoneInBank) : phoneInBank;
		bankcard.setPhoneInBank(phoneInBank);
	}

	/**
	 * 解密
	 * @return void
	 */
	private void doDcryptBankCard(UcUserBankcard bankcard) {
		//cardAccount
		String cardAccount = bankcard.getCardAccount();
		cardAccount = StringUtils.isNotBlank(cardAccount) ? SymmetricEncrypt.decryptStr(cardAccount) : cardAccount;
		bankcard.setCardAccount(cardAccount);
		//accountName
		String accountName = bankcard.getAccountName();
		accountName = StringUtils.isNotBlank(accountName) ? SymmetricEncrypt.decryptStr(accountName) : accountName;
		bankcard.setAccountName(accountName);
		//phoneInBank 
		String phoneInBank = bankcard.getPhoneInBank();
		phoneInBank = StringUtils.isNotBlank(phoneInBank) ? SymmetricEncrypt.decryptStr(phoneInBank) : phoneInBank;
		bankcard.setPhoneInBank(phoneInBank);
	}
}
