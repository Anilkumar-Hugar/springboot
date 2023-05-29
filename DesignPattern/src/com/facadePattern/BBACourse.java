package com.facadePattern;

public class BBACourse implements Course {
	private String BBA;
	private String code = "BBA112";

	@Override
	public String getCourse() {
		System.out.println(code);
		return BBA;
	}

}
