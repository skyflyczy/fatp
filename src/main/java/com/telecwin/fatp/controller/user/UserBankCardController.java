package com.telecwin.fatp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.domain.UcUserBankcard;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.service.sys.SysBankSerialnoService;
import com.telecwin.fatp.service.sys.SysBankService;
import com.telecwin.fatp.service.sys.SysareaCityService;
import com.telecwin.fatp.service.sys.SysareaProvinceService;
/**
 * 发行人银行账户 
 */
@Controller
@RequestMapping("/user/bankcard")
public class UserBankCardController extends UserSupport {

	private String viewPath = super.viewPath + "user/bankcard/";
	
	@Autowired
	private SysareaProvinceService sysareaProvinceService;
	@Autowired
	private SysBankService sysBankService;
	@Autowired
	private SysareaCityService sysareaCityService;
	@Autowired
	private SysBankSerialnoService sysBankSerialnoService;
	
	/**
	 * 银行卡列表
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("list")
	public String list(@RequestParam int userId) {
		super.cardList(userId);
		return viewPath + "bankcard";
	}
	
	/**
	 * 新增页面
	 * @return
	 * @return String
	 */
	@RequestMapping("toAdd")
	@ResponseBody
	public Object toAdd(Integer id,Integer userId) {
		if(id == null && userId == null) {
			return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
		}
		if(id != null) {
			UcUserBankcard bankcard = userBankCardService.getUserBankcardById(userId, id);
			if(bankcard == null) {
				return resultError(ErrorCode.MEMBER_BANKCARD_NOT_EXIST.getMessage());
			}
			request().setAttribute("bankcard", bankcard);
			request().setAttribute("cityList", sysareaCityService.getSystypeCitysByProId(bankcard.getProvinceId()));
			request().setAttribute("bankSerialList", sysBankSerialnoService.getBankSerialnosByBank(bankcard.getBankId(), bankcard.getCityId()));
		}
		UcUser user = ucUserService.getAllById(userId, super.getExchangeId());
		String accountName = null;
		if(user.getOrgTypeId() == OrgType.自然人.type) {
			accountName = user.getRealName();
		}else {
			accountName = user.getCompanyName();
		}
		request().setAttribute("userId", userId);
		request().setAttribute("accountName", accountName);
		request().setAttribute("provinceList", sysareaProvinceService.getAll());
		request().setAttribute("bankList", sysBankService.getDefaultBankList());
		return new ModelAndView(viewPath + "add");
	}
	
	/**
	 * 银行卡增改
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@ModelAttribute UcUserBankcard o) {
		try {
			o.setCardAccount(o.getCardAccount().replace(" ", ""));
			int id = userBankCardService.editUserBankCard(o, getExchangeId(), getSelfId());
			JSONObject obj = resultSuccess();
			obj.put("id", id);
			return obj;
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 银行卡删除
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object delete(@RequestParam int userId, @RequestParam int id) {
		try {
			this.userBankCardService.deleteUserBankCard(userId, id, getSelfId());
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 设置默认卡
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("updateDefault")
	@ResponseBody
	public Object updateDefault(@RequestParam int userId, @RequestParam int cardId) {
		try {
			this.userBankCardService.updateDefaultBankCrad(userId, cardId);
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateDefault", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateDefault", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
}
