package model;

import java.util.Objects;

public class MajorVO {

	private int serial_num;
	private String major_code;
	private String major_name;
	
	public MajorVO() {
		super();
	}
	
	public MajorVO(int serial_num, String major_name) {
		this(serial_num, null, major_name);
	}
	
	public MajorVO(int serial_num, String major_code, String major_name) {
		super();
		this.serial_num = serial_num;
		this.major_code = major_code;
		this.major_name = major_name;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}

	public String getMajor_code() {
		return major_code;
	}

	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	@Override
	public String toString() {
		return "MajorVO [serial_num=" + serial_num + ", major_code=" + major_code + ", major_name=" + major_name + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(serial_num, this.major_code, this.major_name);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MajorVO)) {
			return false;
		}
		MajorVO majorVO = (MajorVO)obj;
		return this.serial_num == majorVO.serial_num && this.major_code == majorVO.major_code &&
			this.major_name == majorVO.major_name;
	}
}
