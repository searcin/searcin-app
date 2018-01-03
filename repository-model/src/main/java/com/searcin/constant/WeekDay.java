package com.searcin.constant;

public enum WeekDay {
	SUNDAY(1,"Sunday"),MONDAY(2,"Monday"),TUESDAY(3,"Tuesday"),WEDNESDAY(4,"Wednesday"),THURSDAY(5,"Thursday"),FRIDAY(6,"Friday"),SATURDAY(7,"Saturday");
	
	private Integer value;
	private String label;
	
	WeekDay(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public String getLabel() {
		return this.label;
	}

	public static String getLabelByDay(Integer weekDay) {
		String label = null;
		for(WeekDay item:WeekDay.values()) {
			if(item.getValue().equals(weekDay)) {
				label = item.getLabel();
				break;
			}
		}
		return label;
	}
}
