package com.telecwin.fatp.domain;

import com.telecwin.fatp.po.sys.SysBankSerialnoPo;

/**
 * SysBankSerialno
 * @author auto-generator
 * 2016-02-01 32:15:17
 */
public class SysBankSerialno extends SysBankSerialnoPo {
	private static final long serialVersionUID = 1L;
	/**
	 * 银行名称
	 */
	private String bankName;

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}

