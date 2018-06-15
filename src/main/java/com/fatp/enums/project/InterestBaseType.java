package com.fatp.enums.project;

/**
 * 计算基准类型枚举
 *
 */
public enum InterestBaseType {
    /**
     * 客户自定义计息基准天
     */
    Custom(0),

    /**
     * 年实际天数
     */
    ActualDays(1);

    public int type;

    private InterestBaseType(int type){
        this.type = type;
    }

    public static InterestBaseType fromType(int type){
        for(InterestBaseType interestBaseType:values()) {
            if(interestBaseType.type == type) {
                return interestBaseType;
            }
        }
        throw new IllegalArgumentException("【"+type+"】不存在的计息基准类型");
    }
}
