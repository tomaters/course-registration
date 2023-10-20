package model;

import java.util.Objects;

public class CourseVO {

	private int serial_num;
	private String course_abv;
	private String course_name;
	
	public CourseVO() {
		super();
	}

	public CourseVO(int serial_num, String course_abv, String course_name) {
		super();
		this.serial_num = serial_num;
		this.course_abv = course_abv;
		this.course_name = course_name;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}

	public String getCourse_abv() {
		return course_abv;
	}

	public void setCourse_abv(String course_abv) {
		this.course_abv = course_abv;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	@Override
	public String toString() {
		return "CourseVO [serial_num=" + serial_num + ", course_abv=" + course_abv + ", course_name=" + course_name
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(serial_num);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CourseVO)) {
			return false;
		}
		CourseVO courseVO = (CourseVO)obj;
		return this.serial_num == courseVO.serial_num;
	}
}
