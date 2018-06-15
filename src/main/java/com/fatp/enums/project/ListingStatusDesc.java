package com.fatp.enums.project;

import com.fatp.enums.FlowFeedTypeDesc;

public enum ListingStatusDesc {
	待提交(1),
	待审核(2),
	审核不通过(3),
	审核退回(4),
	待发布(5),
	已发布(6),
	认购中(7),
	认购结束(8),
	发行成功(9),
	发行失败(-1),
	取消发行(-2),
	项目作废(-3);
	public int value;

    private ListingStatusDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getDesc(int value) {
    	ListingStatusDesc[] array = ListingStatusDesc.values();
    	for(int i=0; i<array.length; i++) {
    		if(array[i].value == value) {
    			return array[i].toString();
    		}
    	}
    	return "";
    }
   /**
    * 项目状态对应的动态状态
    * @param projectStatus
    * @return int
    */
    public static int getFlowFeedType(int projectStatus) {
    	int feedType = FlowFeedTypeDesc.编辑保存.getType();
    	if(projectStatus == ListingStatusDesc.待提交.value) {
    		feedType = FlowFeedTypeDesc.编辑保存.getType();
		}else if(projectStatus == ListingStatusDesc.待审核.value) {
			feedType = FlowFeedTypeDesc.提交审核.getType();
		}else if(projectStatus == ListingStatusDesc.审核不通过.value) {
			feedType = FlowFeedTypeDesc.审核退回.getType();
		}else if(projectStatus == ListingStatusDesc.待发布.value) {
			feedType = FlowFeedTypeDesc.审核通过.getType();
		}else if(projectStatus == ListingStatusDesc.取消发行.value) {
			feedType = FlowFeedTypeDesc.取消发行.getType();
		}
		return feedType;
    }
    
    public static ListingStatusDesc fromValue(int value) {
		for (ListingStatusDesc item : ListingStatusDesc.values()) {
			if (item.value == value) {
				return item;
			}
		}
		return null;
	}
}
