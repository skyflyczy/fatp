package com.telecwin.fatp.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.SysBankSerialno;
import com.telecwin.fatp.enums.YesNo;
import com.telecwin.fatp.po.sys.SysBankPo;
import com.telecwin.fatp.service.sys.SysBankSerialnoService;
import com.telecwin.fatp.service.sys.SysBankService;

@Controller
@RequestMapping("/sys/bank")
public class SysBankController extends BaseController {

	@Autowired
	private SysBankService sysBankService;
	@Autowired
	private SysBankSerialnoService sysBankSerialnoService;
	
	private String viewPath = super.viewPath + "sys/bank/";
	/**
	 * 查找银行卡列表
	 * @return
	 */
	@RequestMapping("/search")
	public String search(){
		Map<String,Object> map = paramToMap(request());
		if(!map.containsKey("bankName") && !map.containsKey("py")) {
			request().setAttribute("list", sysBankService.getDefaultBankList());
		}else {
			map.put("sortColumns", "sys_bank.ShowIndex IS NULL,sys_bank.ShowIndex,sys_bank.id desc");
			map.put("useEnabled", YesNo.是.value);
			map.remove("pageCurrent");
			map.remove("pageSize");
			List<SysBankPo> list= sysBankService.getBankList(map);
			request().setAttribute("list", list);
		}
		request().setAttribute("search", map);
		return viewPath + "search";
	}
	/**
	 * 查找支行列表
	 * @param bankId
	 * @param cityId
	 * @return
	 */
	@RequestMapping("/bankserialno")
	@ResponseBody
	public Object bankserialno(Integer bankId, Integer cityId){
		JSONArray json = new JSONArray();
		if(bankId != null && cityId != null && cityId != 0) {
			List<SysBankSerialno> list = sysBankSerialnoService.getBankSerialnosByBank(bankId, cityId);
			for(SysBankSerialno o : list) {
				JSONObject obj = new JSONObject();
				obj.put("value", o.getId());
				obj.put("label", o.getSubBankName());
				json.add(obj);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("value", "");
		obj.put("label", "--请选择--");
		json.add(0, obj);
		return json;
	}
}
