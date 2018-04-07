package com.telecwin.fatp.enums.project;

/**
 * 项目文件类型
 */
public enum ProjectFileType {

	附件(1);
	
	public int type;
	
	private ProjectFileType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
}
