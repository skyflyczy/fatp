package com.telecwin.fatp.domain.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telecwin.fatp.po.project.ProjectFilesPo;

public class ProjectFiles extends ProjectFilesPo {
	/**
	 * 附件1
	 */
	private java.lang.String file1Str;
	/**
	 * 附件2
	 */
	private java.lang.String file2Str;
	/**
	 * 附件3
	 */
	private java.lang.String file3Str;
	/**
	 * 附件4
	 */
	private java.lang.String file4Str;
	/**
	 * 附件5
	 */
	private java.lang.String file5Str;
	
	private String typeName;
	public List<String> getFileList(){
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isBlank(super.getFile1())){
			list.add(super.getFile1());
		}
		if(!StringUtils.isBlank(super.getFile2())){
			list.add(super.getFile2());
		}
		if(!StringUtils.isBlank(super.getFile3())){
			list.add(super.getFile3());
		}
		if(!StringUtils.isBlank(super.getFile4())){
			list.add(super.getFile4());
		}
		if(!StringUtils.isBlank(super.getFile5())){
			list.add(super.getFile5());
		}
		return list;
	}
	public String allFileNames() {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotEmpty(super.getFile1()))
			sb.append(super.getFile1());
		if(StringUtils.isNotEmpty(super.getFile2()))
			sb.append("|").append(super.getFile2());
		if(StringUtils.isNotEmpty(super.getFile3()))
			sb.append("|").append(super.getFile3());
		if(StringUtils.isNotEmpty(super.getFile4()))
			sb.append("|").append(super.getFile4());
		if(StringUtils.isNotEmpty(super.getFile5()))
			sb.append("|").append(super.getFile5());
		return sb.toString();
	}

	public String file1Show() {
		String[] arr = super.getFile1().split(":");
		if(arr.length == 1)
			return super.getFile1();
		else
			return arr[0];
	}
	
	public String file1Link() {
		String[] arr = super.getFile1().split(":");
		if(arr.length == 1)
			return super.getFile1();
		else
			return arr[1];
	}
	
	public String file2Show() {
		String[] arr = super.getFile2().split(":");
		if(arr.length == 1)
			return super.getFile2();
		else
			return arr[0];
	}
	
	public String file2Link() {
		String[] arr = super.getFile2().split(":");
		if(arr.length == 1)
			return super.getFile2();
		else
			return arr[1];
	}
	
	public String file3Show() {
		String[] arr = super.getFile3().split(":");
		if(arr.length == 1)
			return super.getFile3();
		else
			return arr[0];
	}
	
	public String file3Link() {
		String[] arr = super.getFile3().split(":");
		if(arr.length == 1)
			return super.getFile3();
		else
			return arr[1];
	}
	
	public String file4Show() {
		String[] arr = super.getFile4().split(":");
		if(arr.length == 1)
			return super.getFile4();
		else
			return arr[0];
	}
	
	public String file4Link() {
		String[] arr = super.getFile4().split(":");
		if(arr.length == 1)
			return super.getFile4();
		else
			return arr[1];
	}
	
	public String file5Show() {
		String[] arr = super.getFile5().split(":");
		if(arr.length == 1)
			return super.getFile5();
		else
			return arr[0];
	}
	
	public String file5Link() {
		String[] arr = super.getFile5().split(":");
		if(arr.length == 1)
			return super.getFile5();
		else
			return arr[1];
	}
	public java.lang.String getFile1Str() {
		return file1Str;
	}
	public java.lang.String getFile2Str() {
		return file2Str;
	}
	public java.lang.String getFile3Str() {
		return file3Str;
	}
	public java.lang.String getFile4Str() {
		return file4Str;
	}
	public java.lang.String getFile5Str() {
		return file5Str;
	}
	public void setFile1Str(java.lang.String file1Str) {
		this.file1Str = file1Str;
	}
	public void setFile2Str(java.lang.String file2Str) {
		this.file2Str = file2Str;
	}
	public void setFile3Str(java.lang.String file3Str) {
		this.file3Str = file3Str;
	}
	public void setFile4Str(java.lang.String file4Str) {
		this.file4Str = file4Str;
	}
	public void setFile5Str(java.lang.String file5Str) {
		this.file5Str = file5Str;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
