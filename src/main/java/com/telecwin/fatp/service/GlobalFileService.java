package com.telecwin.fatp.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.domain.GlobalFile;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.GlobalFilePo;
import com.telecwin.fatp.service.datasupprot.GlobalFileDataSupportService;
import com.telecwin.fatp.util.FileUtil;

@Service
public class GlobalFileService {

	@Autowired
	private GlobalFileDataSupportService globalFileDataSupportService;
	
	public GlobalFile getGlobalFileById(int id) {
		return globalFileDataSupportService.getGlobalFileById(id);
	}
	/**
	 * 插入全局文件数据表
	 * @param filePath
	 * @param originalFileName
	 * @param memberId
	 * @param operatorId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public GlobalFilePo insertGlobalFile(String filePath,String originalFileName,int memberId,int operatorId) {
		GlobalFilePo file = new GlobalFilePo();
		file.setContentType(FileUtil.contentType(originalFileName));
		file.setContentType(FileUtil.contentType(originalFileName));
		file.setCreateOperatorId(operatorId);
		file.setCreateUserId(memberId);
		file.setDisplayName(originalFileName);
		file.setFilePath(filePath);
		file.setOriginalFileName(originalFileName);
		file.setDisplayName(originalFileName);//原始文件名即为显示名称
		int id = globalFileDataSupportService.insertGlobalFile(file);
		if(id <= 0) {
			throw new FatpException(ErrorCode.FILE_SAVE_FAIL);
		}
		return file;
	}
	/**
	 * 根据Id删除文件
	 * @param id
	 */
	public void deleteGlobalFileById(int id) {
		globalFileDataSupportService.deleteById(id);
	}
}
