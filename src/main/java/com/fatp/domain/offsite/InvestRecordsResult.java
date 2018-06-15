package com.fatp.domain.offsite;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvestRecordsResult {
	
	private int totalNum;//总条数
	
	private BigDecimal totalMoney;//总金额
	
	//总投资人数
	private Integer totalInvesters;
	
	private Map<String, String> duplicateMap = new HashMap<String, String>();

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getTotalInvesters() {
		return totalInvesters;
	}

	public void setTotalInvesters(Integer totalInvesters) {
		this.totalInvesters = totalInvesters;
	}

	public Map<String, String> getDuplicateMap() {
		return duplicateMap;
	}

	public void setDuplicateMap(Map<String, String> duplicateMap) {
		this.duplicateMap = duplicateMap;
	}

	public void setDuplicateMap(String key, int rowNumber) {
		if(this.duplicateMap.containsKey(key)) {
			this.duplicateMap.put(key, this.duplicateMap.get(key)+","+String.valueOf(rowNumber));
		}else {
			this.duplicateMap.put(key, String.valueOf(rowNumber));
		}
	}
	
	public String getDuplicateString() {
		StringBuilder sb = new StringBuilder();
		for(String s : duplicateMap.values()) {
			sb.append(s).append("行;");
		}
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	public int getDuplicateMapSize() {
		return this.duplicateMap.size();
	}
}
