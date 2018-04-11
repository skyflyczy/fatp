package com.telecwin.fatp.controller.project.listing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
/**
 * 挂牌接口
 * 
 */
@Controller
@RequestMapping("/project/listing/")
public class ListingController extends ListingSupport{

	private String viewPath = super.viewPath + "project/listing";
	/**
	 * 查看挂牌产品
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("view")
	public String view(int id) {
		ListingComplex listing = super.listingView(id);
		if(listing.getProductTypeId().intValue() == ProductTypeDesc.收益权转让计划.value) {
			return viewPath + "/syq/proinfo-index";
		} else if(listing.getProductTypeId().intValue() == ProductTypeDesc.定向融资计划.value){
			return viewPath + "/dxrz/proinfo-index";
		}
		//TODO 给一个默认页
		return "";
	}
}
