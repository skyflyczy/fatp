package com.fatp.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.po.sys.SysbizcodeSequencePo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SysbizcodeSequenceDataSupportService;
import com.huajin.baymax.exception.ExceptionThrowUtil;
import com.huajin.baymax.support.ResponseCodeBase;
import com.huajin.baymax.util.DateUtils;

@Service
public class SysbizcodeSequenceService extends BaseService {
	@Autowired
	private SysbizcodeSequenceDataSupportService sysbizcodeSequenceDataSupportService;
	
	@Transactional(rollbackFor = Exception.class)
	public String getBizapplyRepaySequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(11);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() + o.getCodeSeqDate() + StringUtils.leftPad(o.getCurrentVal().toString(), 8, "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getBizapplyPayinvestSequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(12);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		int length = o.getCodeLength()-o.getCodePrefix().length() - o.getCodeSeqDate().toString().length();
		return o.getCodePrefix() + o.getCodeSeqDate() + StringUtils.leftPad(o.getCurrentVal().toString(), length, "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getBizmoneyDrawbackSequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(10);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		int length = o.getCodeLength()-o.getCodePrefix().length() - o.getCodeSeqDate().toString().length();
		return o.getCodePrefix() + o.getCodeSeqDate() + StringUtils.leftPad(o.getCurrentVal().toString(), length, "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getCustomerSequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(38);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(String.valueOf(RandomUtils.nextInt(1, 100)), 2, "0") + o.getCurrentVal().toString();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getMemberSequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(37);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(String.valueOf(RandomUtils.nextInt(1, 100)), 2, "0") + o.getCurrentVal().toString();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getOperatorSequence() {
		SysbizcodeSequencePo o = this.sysbizcodeSequenceDataSupportService.getById(36);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(String.valueOf(RandomUtils.nextInt(1, 100)), 2, "0") + o.getCurrentVal().toString();
	}
	/**
	 * 生成挂牌代码
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getListingInfoSequence() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(22);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(o.getCurrentVal().toString(), (o.getCodeLength()-o.getCodePrefix().length()), "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getRecordSequence() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(40);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(o.getCurrentVal().toString(), (o.getCodeLength()-o.getCodePrefix().length()), "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getBasicAssetSequence() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(42);
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() +  StringUtils.leftPad(o.getCurrentVal().toString(), (o.getCodeLength()-o.getCodePrefix().length()), "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getProjectSettleCode() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(24);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		int length = o.getCodeLength()-o.getCodePrefix().length() - o.getCodeSeqDate().toString().length();
		return o.getCodePrefix() + o.getCodeSeqDate() +  StringUtils.leftPad(o.getCurrentVal().toString(), length, "0");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String getPlanRepaySequence(String projectCode, String periodNumber) {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(23);
//		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
//		sysbizcodeSequenceDao.update(o);
		return projectCode +  StringUtils.leftPad(periodNumber, (o.getCodeLength()-periodNumber.length()), "0");
	}
	public List<SysbizcodeSequencePo> getSysBizcodeSequenceList(){
		return this.sysbizcodeSequenceDataSupportService.select(null);
	}
	/**
	 * 业务流水号-放款
	 * @return
	 * @return String
	 * @author zhiya.chai
	 * 2015年8月12日 下午3:30:21
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getReleaseBizNumber() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(27);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		int length = o.getCodeLength()-o.getCodePrefix().length() - o.getCodeSeqDate().toString().length();
		return o.getCodePrefix() + o.getCodeSeqDate() +  StringUtils.leftPad(o.getCurrentVal().toString(), length, "0");
	}
	/**
	 * 转让结算编号
	 * @return
	 * @return String
	 * @author zhiya.chai
	 * 2015年8月12日 下午3:30:21
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getTransferSettleCode() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(28);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		int length = o.getCodeLength()-o.getCodePrefix().length() - o.getCodeSeqDate().toString().length();
		return o.getCodePrefix() + o.getCodeSeqDate() +  StringUtils.leftPad(o.getCurrentVal().toString(), length, "0");
	}
	/**
	 * 订单流水号
	 * @return
	 * @return String
	 * @author zhiya.chai
	 * 2015年8月20日 下午2:03:46
	 */
	@Transactional(rollbackFor = Exception.class)
	public String getBizOrderApplySequence() {
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(9);
		o.setCodeSeqDate(Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd")));
		o.setCurrentVal(o.getCurrentVal()+o.getIncrementVal());
		sysbizcodeSequenceDataSupportService.update(o);
		return o.getCodePrefix() + o.getCodeSeqDate() + StringUtils.leftPad(o.getCurrentVal().toString(), 8, "0");
	}

	/**
	 * 获取批量的项目编码
	 * @return List<String>
	 * @author zhiya.chai
	 * 2017年3月1日 下午6:47:08
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<String> getBatchProjectSequence(int number) {
		if(number == 0){
			throw ExceptionThrowUtil.obtainException(null, ResponseCodeBase.SYSTEM_ERROR, "number必须大于0!");
		}
		SysbizcodeSequencePo o =  this.sysbizcodeSequenceDataSupportService.getById(22);
		int currentVal = o.getCurrentVal();
		int incrementVal = o.getIncrementVal();
		String codePrefix = o.getCodePrefix();
		int codePrefixLength = o.getCodePrefix().length();
		int codeLenth = o.getCodeLength();
		List<String> codeList = new ArrayList<String>();
		String projectcode = null;
		for(int i = 1; i <= number; i++){
			projectcode = codePrefix +  StringUtils.leftPad(String.valueOf(currentVal + incrementVal * i), (codeLenth-codePrefixLength), "0");
			codeList.add(projectcode);
		}
		o.setCurrentVal(currentVal + incrementVal * number );
		sysbizcodeSequenceDataSupportService.update(o);
		return codeList;
	
	}
}
