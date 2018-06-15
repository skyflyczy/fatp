package com.fatp.service.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.SysBankSerialno;
import com.fatp.domain.UcUser;
import com.fatp.domain.UcUserBankcard;
import com.fatp.enums.YesNo;
import com.fatp.enums.user.CardStatusDesc;
import com.fatp.enums.user.OrgType;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.sys.SysareaCityPo;
import com.fatp.po.sys.SysareaProvincePo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.user.UcUserBankcardDataSupportService;
import com.fatp.service.sys.SysBankSerialnoService;
import com.fatp.service.sys.SysareaCityService;
import com.fatp.service.sys.SysareaProvinceService;
import com.fatp.util.UUIDUtil;
import com.huajin.baymax.util.DateUtils;
/**
 * 发行人银行服务 
 */
@Service
public class UserBankCardService extends BaseService{

	@Autowired
	private UcUserBankcardDataSupportService ucUserBankcardDataSupportService;
	@Autowired
	private SysBankSerialnoService sysBankSerialnoService;
	@Autowired
	protected UcUserService ucUserService;
	@Autowired
	private SysareaProvinceService sysareaProvinceService;
	@Autowired
	private SysareaCityService sysareaCityService;
	
	/**
	 * 获取用户银行卡信息
	 * @param userId
	 * @param exchangeId
	 * @return
	 */
	public List<UcUserBankcard> getUserBankcards(Integer userId,int exchangeId) {
		if(userId == null) {
			return null;
		}
		return ucUserBankcardDataSupportService.getUserBankcards(userId, exchangeId);
	}
	/**
	 * 获取用户的某张卡
	 * @param userId
	 * @param id
	 * @return
	 */
	public UcUserBankcard getUserBankcardById(Integer userId,Integer id) {
		if(userId == null || id == null){
			return null;
		}
		return ucUserBankcardDataSupportService.getUserBankcardById(userId, id);
	}
	/**
	 * 编辑用户银行卡
	 * @param newBankCard
	 * @param exchangeId
	 * @param operatorId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int editUserBankCard(UcUserBankcard newBankCard,int exchangeId,int operatorId) {
		//验证分支行
		SysBankSerialno subbank = validBankSerialno(newBankCard);
		newBankCard.setSubBankName(subbank.getSubBankName());
		
		//验证是否已经存在
		validUserBankCardExist(newBankCard);
		
		//获取用户得到银行账户名，银行账户名称不接受页面传入参数
		UcUser user = ucUserService.getAllById(newBankCard.getUserId(), exchangeId);
		String accountName = user.getOrgTypeId() == OrgType.自然人.type ? user.getRealName() : user.getCompanyName();
		newBankCard.setAccountName(accountName);
		
		//获取省份、城市信息
		if(newBankCard.getProvinceId() != null && newBankCard.getProvinceId() != 0) {
			SysareaProvincePo sysareaProvince = this.sysareaProvinceService.getById(newBankCard.getProvinceId());
			newBankCard.setProvinceName(sysareaProvince != null ? sysareaProvince.getProName() : newBankCard.getProvinceName());
		}
		if(newBankCard.getCityId() != null && newBankCard.getCityId() != 0) {
			SysareaCityPo sysareaCity = sysareaCityService.getById(newBankCard.getCityId());
			newBankCard.setCityName(sysareaCity != null ? sysareaCity.getCityName() : newBankCard.getCityName());
		}
		//编辑
		newBankCard.setIsMyCard(1);
		newBankCard.setCardStatus(CardStatusDesc.正常.value);
		newBankCard.setUpdateOperatorId(operatorId);
		newBankCard.setUpdateTime(new Date());
		if(newBankCard.getId() == null) {
			newBankCard.setCreateOperatorId(operatorId);
			newBankCard.setCreateTime(new Date());
			newBankCard.setUserCardGuid(UUIDUtil.getUUID());
			//取该人是否有正常的卡，设置默认卡
			List<UcUserBankcard> list = this.ucUserBankcardDataSupportService.getUserCanUseBankCard(newBankCard.getUserId());
			newBankCard.setDefaultCard(CollectionUtils.isEmpty(list) ? YesNo.是.value : YesNo.否.value);
			return this.ucUserBankcardDataSupportService.insert(newBankCard);
		}else {
			UcUserBankcard card =  assumDBCardForUpdate(newBankCard);
			this.ucUserBankcardDataSupportService.update(card);
			return card.getId();
		}
	}
	/**
	 * 获取DB银行卡组装更新对象
	 * @param ucUserBankCard
	 * @return
	 */
	private UcUserBankcard assumDBCardForUpdate(UcUserBankcard ucUserBankCard) {
		UcUserBankcard dbCard = ucUserBankcardDataSupportService.getUserBankcardById(ucUserBankCard.getUserId(), ucUserBankCard.getId());
		dbCard.setIdentifyStatus(YesNo.是.value);
		dbCard.setCardStatus(ucUserBankCard.getCardStatus());
		dbCard.setIsMyCard(ucUserBankCard.getIsMyCard());
		dbCard.setAccountName(ucUserBankCard.getAccountName());
		dbCard.setChannelId(ucUserBankCard.getChannelId());
		dbCard.setBankId(ucUserBankCard.getBankId());
		dbCard.setBankSerialId(ucUserBankCard.getBankSerialId());
		dbCard.setCardAccount(ucUserBankCard.getCardAccount());
		dbCard.setProvinceId(ucUserBankCard.getProvinceId());
		dbCard.setProvinceName(ucUserBankCard.getProvinceName());
		dbCard.setCityId(ucUserBankCard.getCityId());
		dbCard.setCityName(ucUserBankCard.getCityName());
		dbCard.setSubBankName(ucUserBankCard.getSubBankName());
		dbCard.setUpdateOperatorId(ucUserBankCard.getUpdateOperatorId());
		return dbCard;
	}
	/**
	 * 验证分支行
	 * @param o
	 * @return
	 */
	private SysBankSerialno validBankSerialno(UcUserBankcard o){
		if(o.getBankSerialId() == null) {
			throw new FatpException(ErrorCode.MEMBER_BANK_BRANCH_CHOICE_ERROR);
		}
		SysBankSerialno subbank = sysBankSerialnoService.getById(o.getBankSerialId());
		if(subbank == null || subbank.getCityId().intValue() != o.getCityId().intValue()) {
			throw new FatpException(ErrorCode.MEMBER_BANK_BRANCH_CHOICE_ERROR);
		}
		return subbank;
	} 
	/**
	 * 验证银行卡是否存在
	 * @param o
	 */
	private void validUserBankCardExist(UcUserBankcard o){
		List<UcUserBankcard> list = ucUserBankcardDataSupportService.getUserBankCardByCardAccount(o.getUserId(), o.getCardAccount());
		if(CollectionUtils.isEmpty(list)) {
			return;
		}
		UcUserBankcard bank = list.get(0);
		if(o.getId() == null || o.getId().intValue() == bank.getId().intValue()) {
			throw new FatpException(ErrorCode.MEMBER_BANKCARD_ALREADY_EXIST);
		}
	}
	
	/**
	 * 删除银行卡
	 * @param id
	 * @param updateOperatorId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteUserBankCard(Integer userId,Integer id,Integer updateOperatorId) {
		if(userId == null || id == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		UcUserBankcard card = ucUserBankcardDataSupportService.getUserBankcardById(userId, id);
		if(card == null){
			throw new FatpException(ErrorCode.MEMBER_BANKCARD_NOT_EXIST);
		}
		boolean isDefault = card.getDefaultCard() != null && card.getDefaultCard() == 1;
		if(isDefault) { //默认卡不能删除
			throw new FatpException(ErrorCode.MEMBER_DEFAULTCARD_NOT_DEL);
		}
		card.setCardAccount(card.getCardAccount()+"_"+DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
		card.setCardStatus(CardStatusDesc.删除.value);
		card.setDefaultCard(0);
		card.setUpdateOperatorId(updateOperatorId);
		ucUserBankcardDataSupportService.updateCardStatus(card);
	}
	
	/**
	 * 更新默认卡
	 * @param userId
	 * @param cardId
	 * @return 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateDefaultBankCrad(Integer userId, Integer cardId) {
		if(userId == null || cardId == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		//取要更改为默认卡的卡
		UcUserBankcard toDefaultCard = ucUserBankcardDataSupportService.getUserBankcardById(userId, cardId);
		if(toDefaultCard.getCardStatus() != CardStatusDesc.正常.value) { //状态不是正常
			throw new FatpException(ErrorCode.MEMBER_BANKCARD_STATUS_ERROR);
		}
		//取现在的默认卡 
		UcUserBankcard defaultCard = this.ucUserBankcardDataSupportService.getDefaultCard(userId);
		defaultCard.setDefaultCard(YesNo.否.value);
		ucUserBankcardDataSupportService.update(defaultCard);
		toDefaultCard.setDefaultCard(YesNo.是.value);
		ucUserBankcardDataSupportService.update(toDefaultCard);
	}
}
