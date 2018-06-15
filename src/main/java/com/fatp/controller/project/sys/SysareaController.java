package com.fatp.controller.project.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fatp.controller.BaseController;
import com.fatp.po.sys.SysareaCityPo;
import com.fatp.po.sys.SysareaDistrictPo;
import com.fatp.service.sys.SysareaCityService;
import com.fatp.service.sys.SysareaDistrictService;
import com.fatp.service.sys.SysareaProvinceService;

/**
 * 系统地区
 */
@Controller
@RequestMapping("/sys/area")
public class SysareaController extends BaseController{
	
	@Autowired
	private SysareaCityService sysareaCityService;
	@Autowired
	private SysareaProvinceService sysareaProvinceService;
	@Autowired
	private SysareaDistrictService sysareaDistrictService;
	
	/**
	 * 选择市
	 * @param userId
	 * @return
	 * @return Object
	 */
	@RequestMapping("city")
	@ResponseBody
	public Object getSystypeCitys(@RequestParam Integer proId) {
		JSONArray json = new JSONArray();
		if(proId != null) {
			List<SysareaCityPo> list = sysareaCityService.getSystypeCitysByProId(proId);
			for(int i=0; i<list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("value", list.get(i).getCityId());
				obj.put("label", list.get(i).getCityName());
				json.add(obj);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("value", "");
		obj.put("label", "--城市--");
		json.add(0, obj);
		return json;
	}
	
	/**
	 * 选择省
	 * @param userId
	 * @return
	 * @return Object
	 */
	@RequestMapping("province")
	@ResponseBody
	public Object getSystypeProvinces() {
		return sysareaProvinceService.getAll();
	}
	
	/**
	 * 选择县
	 * @param userId
	 * @return
	 * @return Object
	 */
	@RequestMapping("district")
	@ResponseBody
	public Object getSystypeDistricts(@RequestParam Integer cityId) {
		JSONArray json = new JSONArray();
		if(cityId != null) {
			List<SysareaDistrictPo> list = sysareaDistrictService.getListByCityId(cityId);
			for(int i=0; i<list.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("value", list.get(i).getDisId());
				obj.put("label", list.get(i).getDisName());
				json.add(obj);
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("value", "");
		obj.put("label", "--区县--");
		json.add(0, obj);
		return json;
	}
}
