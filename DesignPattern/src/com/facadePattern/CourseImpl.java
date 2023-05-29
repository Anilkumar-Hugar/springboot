package com.facadePattern;

public class CourseImpl {
	private BScCourse bScCourse;
	private BcomCourse bcomCourse;
	private BBACourse bbaCourse;

	public CourseImpl() {
		bScCourse = new BScCourse();
		bcomCourse = new BcomCourse();
		bbaCourse = new BBACourse();
	}

	public String bscCourse() {
		return bScCourse.getCourse();
	}

	public String bcomCourse() {
		return bcomCourse.getCourse();
	}

	public String bbaCourse() {
		return bbaCourse.getCourse();
	}
}
