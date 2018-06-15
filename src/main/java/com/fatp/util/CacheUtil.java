package com.fatp.util;

import java.util.Date;

public class CacheUtil {
	public static final String CACHEKEY_SYSTYPEINDUSTRY = "SystypeIndustry_";
	public static final String CACHEKEY_SYSTYPECOMPANY = "SystypeCompany_";
	public static final String CACHEKEY_SYSAREAPROVINCE = "SysareaProvince_";
	public static final String CACHEKEY_SYSAREACITY = "SysareaCity_";
	public static final String CACHEKEY_SYSAREADISTRICT = "SysareaDistrict_";
	public static final String CACHEKEY_DATASTATUSMSGSETTIN = "DatastatusMsgsetting";
	public static final String CACHEKEY_SYSPARAM = "sysParam_";
	public static final String CACHEKEY_SYS_SMS_CHANNELS = "Sys_Sms_Channels";
	public static final String CACHEKEY_SYSTYPE_TRADES ="Systype_trades";
	public static final String CACHEKEY_SYSEXCEPTION ="SysException";
	public static final String CACHEKEY_SYSBANKSERIALNO = "SysBankSerialno_";
	public static final String CACHEKEY_SYSBANK = "sysBank_";
	
	public static final  Date CACHE_EXPIRY_24HOUR = new Date(24*60*60*1000);
	public static final Date CACHE_EXPIRY_1HOUR = new Date(1*60*60*1000);
	public static final Date FIVE_MINUE_EXPIRED = new Date(5*60*1000);
	public static final Date THREE_MINUE_EXPIRED = new Date(3*60*1000);
}
