/* The [Model] package contains the data and business logic
    - It defines how the data is structured, stored, and implemented
    - It contains the interactions with databases, file systems, and external APIs
    - It tells the View and Controller classes about any changes within the data
 */
package model;

import java.sql.Date;
import java.util.Objects;

// Value Object (VO) classes hold values and provide methods to access and compare them
public class CourseCatalogVO {
	private int serial_num;
	private String student_num;
	private String course_abv;
	private String course_type;
	private Date reg_date;
	
	public CourseCatalogVO() {
		super();
	}

	public CourseCatalogVO(int serial_num, String student_num, String course_abv, String course_type, Date reg_date) {
		super();
		this.serial_num = serial_num;
		this.student_num = student_num;
		this.course_abv = course_abv;
		this.course_type = course_type;
		this.reg_date = reg_date;
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

	public String getCourse_abv() {
		return course_abv;
	}

	public void setCourse_abv(String course_abv) {
		this.course_abv = course_abv;
	}

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "CourseCatalogVO [serial_num=" + serial_num + ", student_num=" + student_num + ", course_abv="
				+ course_abv + ", course_type=" + course_type + ", reg_date=" + reg_date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(serial_num, this.student_num, this.course_abv, this.course_type, this.reg_date);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof CourseCatalogVO)) {
			return false;
		}
		CourseCatalogVO courseCatalogVO = (CourseCatalogVO)obj;
		return this.serial_num == courseCatalogVO.serial_num && this.student_num == courseCatalogVO.student_num &&
			this.course_abv == courseCatalogVO.course_abv && this.course_type == courseCatalogVO.course_type &&
			this.reg_date == courseCatalogVO.reg_date;
	}
}

