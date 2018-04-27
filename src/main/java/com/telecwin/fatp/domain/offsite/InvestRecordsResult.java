package com.telecwin.fatp.domain.offsite;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvestRecordsResult {
	
	private Integer tradeRecordId;//交易总记录id
	
	private int totalNum;//总条数
	
	private BigDecimal totalMoney;//总金额
	
	//总投资人数
	private Integer totalInvesters;
	
	private int overBuyTime;
	
	private int overInvestLimit;
	
	private int lessBuy;
	
	private int notEqAppend;
	
	private Map<String, String> duplicateMap = new HashMap<String, String>();
	
	public Integer getTradeRecordId() {
		return tradeRecordId;
	}

	public void setTradeRecordId(Integer tradeRecordId) {
		this.tradeRecordId = tradeRecordId;
	}

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

	public int getOverBuyTime() {
		return overBuyTime;
	}

	public int getOverInvestLimit() {
		return overInvestLimit;
	}

	public int getLessBuy() {
		return lessBuy;
	}

	public void setOverBuyTime(int overBuyTime) {
		this.overBuyTime = overBuyTime;
	}

	public void setOverInvestLimit(int overInvestLimit) {
		this.overInvestLimit = overInvestLimit;
	}

	public void setLessBuy(int lessBuy) {
		this.lessBuy = lessBuy;
	}

	public int getNotEqAppend() {
		return notEqAppend;
	}

	public void setNotEqAppend(int notEqAppend) {
		this.notEqAppend = notEqAppend;
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
