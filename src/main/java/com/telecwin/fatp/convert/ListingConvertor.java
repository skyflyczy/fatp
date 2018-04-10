package com.telecwin.fatp.convert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.domain.project.ListingSaleagent;
import com.telecwin.fatp.po.project.ListingBasePo;
import com.telecwin.fatp.po.project.ListingClearingPo;
import com.telecwin.fatp.po.project.ListingContentPo;
import com.telecwin.fatp.po.project.ListingSaleagentPo;
import com.telecwin.fatp.po.project.ListingTradePo;

/**
 * 挂牌转换器
 */
public class ListingConvertor {

	/**
	 * 从ListingComplex转换为ListingBasePo 
	 * @param infoVo
	 * @return
	 */
	public static ListingBasePo convertListingBasePo(ListingComplex infoVo) {
		ListingBasePo basePo = new ListingBasePo();
		BeanUtils.copyProperties(infoVo, basePo);
		return basePo;
	} 

	/**
	 * 
	 * 从ListingComplex转换为ListingTradePo 
	 * @param infoVo
	 * @return
	 */
	public static ListingTradePo convertListingTradePo(ListingComplex infoVo) {
		ListingTradePo tradePo = new ListingTradePo();
		BeanUtils.copyProperties(infoVo, tradePo);
		return tradePo;
	}
	
	/**
	 * 从ListingComplex转换为ListingClearingPo 
	 * @param infoVo
	 * @return
	 */
	public static ListingClearingPo convertListingClearingPo(ListingComplex infoVo) {
		ListingClearingPo clearingPo = new ListingClearingPo();
		BeanUtils.copyProperties(infoVo, clearingPo);
		return clearingPo;
	}
	
	/**
	 * 从ListingComplex中装载ContentPo
	 * @param infoVo
	 * @return
	 */
	public static ListingContentPo convertListingContentPo(ListingComplex infoVo) {
		ListingContentPo content = new ListingContentPo();
		if(StringUtils.isNotBlank(infoVo.getProjectInfo())) {
			content.setProjectInfo(infoVo.getProjectInfo().replace("<pre>", "<p>").replace("</pre>", "</p>"));
		}
		content.setTradePartyQualification(infoVo.getTradePartyQualification());
		content.setTradeTerm(infoVo.getTradeTerm());
		content.setBasicAssetNote(infoVo.getBasicAssetNote());
		content.setTransferorBasicNote(infoVo.getTransferorBasicNote());
		return content;
	}
	
	/**
	 * 从infoVo中获取List<ListingSaleagentPo>
	 * @param infoVo
	 * @return
	 */
	public static List<ListingSaleagentPo> convertSaleagentList(ListingComplex infoVo) {
		List<ListingSaleagentPo> saleAgentPoList = new ArrayList<>();
		List<ListingSaleagent> list = infoVo.getProjectSaleagent();
		if(CollectionUtils.isEmpty(list))
			return Collections.emptyList();
		for(ListingSaleagent agent : list) {
			ListingSaleagentPo result = new ListingSaleagentPo();
			result.setId(agent.getId());
			result.setProjectId(agent.getProjectId());
			result.setSaleMemberId(agent.getSaleMemberId());
			result.setSaleTypeId(agent.getSaleTypeId());
			result.setSaleFeeRate(agent.getSaleFeeRate().divide(new BigDecimal("100"), 5,BigDecimal.ROUND_HALF_UP));
			result.setSaleAmount(agent.getSaleMoney());
			result.setSaleMoney(agent.getSaleMoney());
			result.setMemberId(infoVo.getMemberId());
			result.setExchangeId(infoVo.getExchangeId());
			saleAgentPoList.add(result);
		}
		return saleAgentPoList;
	}
}
