package com.telecwin.fatp.enums.project;

public enum PlatformFeeType {

	次(1), 年化(2);

	public int type;

	private PlatformFeeType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public static String getDesc(Integer value) {
		PlatformFeeType[] array = PlatformFeeType.values();
		if (value == null) {
			return "";
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i].type == value) {
				return array[i].toString();
			}
		}
		return "";
	}
}
