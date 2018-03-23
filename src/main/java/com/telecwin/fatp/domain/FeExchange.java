package com.telecwin.fatp.domain;

import com.telecwin.fatp.po.FeExchangePo;

/**
 * FeExchange
 */
public class FeExchange extends FeExchangePo{
	
	private static final long serialVersionUID = -1230153171030983463L;
	
	private String cityName;
	private String proName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}

}

