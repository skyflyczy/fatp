package com.telecwin.fatp.enums.project;

public enum ProjectLimitType {
	天(1),
	月(2),
	年(3);
	
	public Integer type;
	
	private ProjectLimitType(Integer type){
		this.type = type;
	}
	
	public static ProjectLimitType getProjectLimitType(Integer type){
		if(type == null) {
			return ProjectLimitType.天;
		}
		for(ProjectLimitType desc : ProjectLimitType.values()){
			if(desc.type == type){
				return desc;
			}
		}
		return ProjectLimitType.天;
	}
	
	public int getType() {
		return type;
	}
}
