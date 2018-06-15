package com.fatp.util;

import java.math.BigDecimal;

import com.huajin.baymax.encrypt.SymmetricEncrypt;

public class Constant {
	
	public static final Integer SYSAPPID = 1;
	
	public static final String VERIFYCODE_ID = "telec_vc_id";
	public static final String FIELD_TOTAL = "totalsize";
	public static final String FIELD_TOTALPAGE = "totalpage";
    public static final int TRUE_FLAG = 1;
    public static final int FALSE_FLAG = 0;
    
    
	
	public static final String PLATFORM = SymmetricEncrypt.encryptStr("FATP");
	
    public static final int DEFAULT_PAGEINDEX = 1;
    public static final int DEFAULT_PAGESIZE = 20;
    public static final String _PAGEINDEX = "pageCurrent";
	public static final String _PAGESIZE = "pageSize";
	
	 public static final BigDecimal _PLAT_DEFAULT_FEE = new BigDecimal("0.002");
	
}
