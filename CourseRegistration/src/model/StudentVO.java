package model;

import java.sql.Date;
import java.util.Objects;

public class StudentVO {

	private int serial_num;
	private String student_num;
	private String student_name;
	private String student_id;
	private String student_pass;
	private String major_code;
	private String student_bday;
	private String student_phone;
	private String student_address;
	private String student_email;
	private Date student_date;
	
	public StudentVO() {
		super();
	}

	public StudentVO(int serial_num, String student_num, String student_name, String student_id, String student_pass,
			String major_code, String student_bday, String student_phone, String student_address, String student_email,
			Date student_date) {
		super();
		this.serial_num = serial_num;
		this.student_num = student_num;
		this.student_name = student_name;
		this.student_id = student_id;
		this.student_pass = student_pass;
		this.major_code = major_code;
		this.student_bday = student_bday;
		this.student_phone = student_phone;
		this.student_address = student_address;
		this.student_email = student_email;
		this.student_date = student_date;
	}

	public int getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(int serial_num) {
		this.serial_num = serial_num;
	}

	public String getStudent_num() {
		return student_num;
	}

	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudent_pass() {
		return student_pass;
	}

	public void setStudent_pass(String student_pass) {
		this.student_pass = student_pass;
	}

	public String getMajor_code() {
		return major_code;
	}

	public void setMajor_code(String major_code) {
		this.major_code = major_code;
	}

	public String getStudent_bday() {
		return student_bday;
	}

	public void setStudent_bday(String student_bday) {
		this.student_bday = student_bday;
	}

	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public String getStudent_address() {
		return student_address;
	}

	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}

	public String getStudent_email() {
		return student_email;
	}

	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}

	public Date getStudent_date() {
		return student_date;
	}

	public void setStudent_date(Date date) {
		this.student_date = date;
	}

	@Override
	public String toString() {
		return "StudentVO [serial_num=" + serial_num + ", student_num=" + student_num + ", student_name=" + student_name
				+ ", student_id=" + student_id + ", student_pass=" + student_pass + ", major_code=" + major_code
				+ ", student_bday=" + student_bday + ", student_phone=" + student_phone + ", student_address="
				+ student_address + ", student_email=" + student_email + ", student_date=" + student_date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.serial_num, this.student_num, this.student_name, this.student_id, this.student_pass,
				this.major_code, this.student_bday, this.student_phone, this.student_address, this.student_email, this.student_date);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StudentVO)) {
			return false;
		}
		StudentVO studentVO = (StudentVO)obj;
		return this.serial_num == studentVO.serial_num && this.student_num == studentVO.student_num && this.student_name == studentVO.student_name &&
				this.student_id == studentVO.student_id && this.student_pass == studentVO.student_pass &&
				this.major_code == studentVO.major_code && this.student_bday == studentVO.student_bday &&
				this.student_phone == studentVO.student_phone && this.student_address == studentVO.student_address &&
				this.student_email == studentVO.student_email && this.student_date == studentVO.student_date;
		}
	}
