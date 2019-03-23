package com.ruoyi.common.enums;

/**
 * 记录软删除
 * @author lsy
 *
 */
public enum DeleteFlagEnum implements IEnum {
	/**
	 * 未删除
	 */
	NORMAL(0),
	/**
	 * 已删除
	 */
	DELETED(1);
	
	private final int value;
	DeleteFlagEnum(final int value) {
		this.value = value;
	}
	@Override
	public int getValue() {
		return this.value;
	}

}
