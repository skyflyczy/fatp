package com.telecwin.fatp.service.datasupprot.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.encrypt.SymmetricEncrypt;
import com.telecwin.fatp.controller.param.OperatorQueryParam;
import com.telecwin.fatp.dao.user.MemberOperatorDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.util.MapUtil;

@Service
public class MemberOperatorDataSupportService {
	
	@Autowired
	private MemberOperatorDao memberOperatorDao;
	
	/**
	 * 根据登录名称获取操作员信息
	 * @param loginName
	 * @param exchangeId
	 * @param operatorStatusArray
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorByLoginName(String loginName,int exchangeId,int[] operatorStatusArray) {
		Map<String,Object> map = new HashMap<>();
		map.put("loginName", loginName);
		map.put("exchangeId", exchangeId);
		map.put("operatorStatusList", operatorStatusArray);
		return getByMap(map);
	}
	/**
	 * 根据Id获取操作员信息
	 * @param id
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorById(int id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		MemberOperatorPo po = memberOperatorDao.getById(map);
		doDecrypt(po);
		return po;
	}
	
	/**
	 * 更新操作员
	 * @param operator
	 * @return
	 */
	public int updateMemberOperator(MemberOperatorPo operator) {
		doEncrypt(operator);
		int n = memberOperatorDao.update(operator);
		doDecrypt(operator);
		return n;
	}
	/**
	 * 插入操作员
	 * @param operator
	 * @return
	 */
	public int insertMemberOperator(MemberOperatorPo operator){
		doEncrypt(operator);
		int n = memberOperatorDao.insert(operator);
		doDecrypt(operator);
		return n;
	}
	/**
	 * 根据手机号获取信息
	 * @param phone
	 * @param memberId
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorByPhone(String phone,int memberId) {
		Map<String,Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("phone",phone);
		return getByMap(map);
	}
	/**
	 * 根据idNumber获取信息
	 * @param phone
	 * @param memberId
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorByIdNumber(String idNumber,Integer idType,int memberId) {
		Map<String,Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("idType", idType);
		map.put("idNumber",idNumber);
		return getByMap(map);
	}
	/**
	 * 根据邮箱获取信息
	 * @param phone
	 * @param memberId
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorByEmail(String email,int memberId) {
		Map<String,Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("email", email);
		return getByMap(map);
	}
	/**
	 * 根据登录名获取信息
	 * @param loginName
	 * @param memberId
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorByLoginName(String loginName,int memberId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("loginName", loginName);
		return getByMap(map);
	}
	/**
	 * 分页查找信息
	 * @param param
	 * @return
	 */
	public PageData<MemberOperatorPo> pageFindByQueryParam(OperatorQueryParam param){
		Map<String,Object> map = MapUtil.getValueMap(param);
		doEncryptByMap(map);
		Page<?> page = PageHelper.startPage(param.getPageCurrent(), param.getPageSize(), true);
		List<MemberOperatorPo> list = memberOperatorDao.select(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(MemberOperatorPo po : list) {
				doDecrypt(po);
			}
		}
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	
	
	private MemberOperatorPo getByMap(Map<String,Object> map) {
		doEncryptByMap(map);
		List<MemberOperatorPo> list = memberOperatorDao.select(map);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		MemberOperatorPo po = list.get(0);
		doDecrypt(po);
		return po;
	}
	
	/**
	 * 加密
	 * @param map
	 */
	private void doEncryptByMap(Map<String,Object> map) {
		if(map.get("loginName") != null && StringUtils.isNotBlank(map.get("loginName").toString())) {
			map.put("loginName",SymmetricEncrypt.encryptStr(map.get("loginName").toString()));
		}
		if(map.get("email") != null && StringUtils.isNotBlank(map.get("email").toString())) {
			map.put("email",SymmetricEncrypt.encryptStr(map.get("email").toString()));
		}
		if(map.get("phone") != null && StringUtils.isNotBlank(map.get("phone").toString())) {
			map.put("phone",SymmetricEncrypt.encryptStr(map.get("phone").toString()));
		}
		if(map.get("idNumber") != null && StringUtils.isNotBlank(map.get("idNumber").toString())) {
			map.put("idNumber",SymmetricEncrypt.encryptStr(map.get("idNumber").toString()));
		}
	}
	/**
	 * 解密
	 * @param o
	 */
	private void doDecrypt(MemberOperatorPo o){
		o.setLoginName(StringUtils.isNotBlank(o.getLoginName())?SymmetricEncrypt.decryptStr(o.getLoginName()):o.getLoginName());
		o.setPhone(StringUtils.isNotBlank(o.getPhone())?SymmetricEncrypt.decryptStr(o.getPhone()):o.getPhone());
		o.setIdNumber(StringUtils.isNotBlank(o.getIdNumber())?SymmetricEncrypt.decryptStr(o.getIdNumber()):o.getIdNumber());
		o.setEmail(StringUtils.isNotBlank(o.getEmail())?SymmetricEncrypt.decryptStr(o.getEmail()):o.getEmail());
	}
	/**
	 * 加密
	 * @param o
	 */
	private void doEncrypt(MemberOperatorPo o){
		o.setLoginName(StringUtils.isNotBlank(o.getLoginName())?SymmetricEncrypt.encryptStr(o.getLoginName()):o.getLoginName());
		o.setPhone(StringUtils.isNotBlank(o.getPhone())?SymmetricEncrypt.encryptStr(o.getPhone()):o.getPhone());
		o.setIdNumber(StringUtils.isNotBlank(o.getIdNumber())?SymmetricEncrypt.encryptStr(o.getIdNumber()):o.getIdNumber());
		o.setEmail(StringUtils.isNotBlank(o.getEmail())?SymmetricEncrypt.encryptStr(o.getEmail()):o.getEmail());
	}

}
