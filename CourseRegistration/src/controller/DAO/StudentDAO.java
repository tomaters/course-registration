package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.DBUtil;
import model.StudentVO;

public class StudentDAO {

	public static Scanner scan = new Scanner(System.in);
	
	public void viewStudentInfo(String username, String password) throws Exception {
		// viewing student information will require user to login
		String selectStatement = "SELECT * FROM student WHERE student_id = ? AND student_pass = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StudentVO studentVO = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			// if resultSet exists (ID/PW are correct), print student info; else print login failed
			if(resultSet.next()) {
				studentVO = new StudentVO();
				studentVO.setSerial_num(resultSet.getInt("serial_num"));
				studentVO.setStudent_num(resultSet.getString("student_num"));
				studentVO.setStudent_name(resultSet.getString("student_name"));
				studentVO.setStudent_id(resultSet.getString("student_id"));
				studentVO.setStudent_pass(resultSet.getString("student_pass"));
				studentVO.setMajor_code(resultSet.getString("major_code"));
				studentVO.setStudent_bday(resultSet.getString("student_bday"));
				studentVO.setStudent_phone(resultSet.getString("student_phone"));
				studentVO.setStudent_address(resultSet.getString("student_address"));
				studentVO.setStudent_email(resultSet.getString("student_email"));
				studentVO.setStudent_date(resultSet.getDate("student_date"));
				// print student info
				System.out.printf("[Serial Number]: %d, [Student Number]: %s, [Student Name]: %s [Student Major Code]: %s%n" +
					"[Student ID]: %s, [Student Birthday]: %s, [Student Phone]: %s%n" +
					"[Student Address]: %s, [Student Email]: %s, [Student Date]: %s%n", 
					studentVO.getSerial_num(), studentVO.getStudent_num(), studentVO.getStudent_name(), studentVO.getMajor_code(),
					studentVO.getStudent_id(), studentVO.getStudent_bday(), studentVO.getStudent_phone(),
					studentVO.getStudent_address(), studentVO.getStudent_email(), studentVO.getStudent_date());
			} else {
				// there will also be a check in StudentRegisterManager so this may not be necessary
				System.out.println("Login failed. Try again");
				return;
			}
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void addStudentInfo(StudentVO studentVO) throws Exception {
		String insertStatement = "INSERT INTO student VALUES(student_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, studentVO.getStudent_num());
			preparedStatement.setString(2, studentVO.getStudent_name());
			preparedStatement.setString(3, studentVO.getStudent_id());
			preparedStatement.setString(4, studentVO.getStudent_pass());
			preparedStatement.setString(5, studentVO.getMajor_code());
			preparedStatement.setString(6, studentVO.getStudent_bday());
			preparedStatement.setString(7, studentVO.getStudent_phone());
			preparedStatement.setString(8, studentVO.getStudent_address());
			preparedStatement.setString(9, studentVO.getStudent_email());
			
			int result = preparedStatement.executeUpdate();
			if(result == 1) {
				System.out.println("Student added successfully");
			} else System.out.println("Failed to add student. Try again");
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void editStudentInfo(StudentVO studentVO) throws Exception {
		// update student information
		String updateStatement = "UPDATE student SET student_pass = ?, student_phone = ?, student_address = ?,"
				+ "student_email = ? WHERE student_num = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, studentVO.getStudent_pass());
			preparedStatement.setString(2, studentVO.getStudent_phone());
			preparedStatement.setString(3, studentVO.getStudent_address());
			preparedStatement.setString(4, studentVO.getStudent_email());
			preparedStatement.setString(5, studentVO.getStudent_num());
			
			int result = preparedStatement.executeUpdate();
			
			if(result == 1) {
				System.out.println(studentVO.getStudent_num() + " Student update successful");
			} else System.out.println(studentVO.getStudent_num() + " Student update failed");
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} catch(Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public String studentCount(String major_code) throws Exception {
		// use of LPAD ensures that the result is always 4 characters long (pads with 0s) 
		// statement counts number of tuples in student where major_code is ? then adds 1 to the count
		// reads as studentCount rather than the previously assigned name
		String selectStatement = "SELECT LPAD(COUNT(*) + 1, 4, '0') as studentCount from student where major_code = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String serial_num = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, major_code);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				serial_num = resultSet.getString("studentCount");
			}
		} catch(SQLException e) {
			System.out.println("SQL Error");
		}
		return serial_num;
	}
	
	public boolean doesUsernameOverlap(String idToRead) throws Exception {
		// input student ID to view student information
		String selectStatement = "SELECT * FROM student WHERE student_id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean usernameOverlaps = false;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, idToRead);
			resultSet = preparedStatement.executeQuery();
			// if the resultSet lands somewhere, the username already exists
			if(resultSet.next()) {
				usernameOverlaps = true;
			}
			
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} catch(Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if(connection != null) connection.close();
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
		return usernameOverlaps;
	}

	public void viewAllStudents() throws Exception {
		// view all students function after admin password
		// INNER JOIN statement to select intersection between tables 'student' and 'major'
		String selectStatement = "SELECT ST.serial_num AS serial_num, student_num, student_name, student_id, student_pass,"
				+ "MJ.major_code AS major_code, student_bday, student_phone, student_address, student_email, student_date"
				+ "FROM student ST, major MJ WHERE ST.serial_num = MJ.major_code ORDER BY serial_num";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StudentVO studentVO = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			// save values from database into Value Object
			while(resultSet.next()) {
				studentVO = new StudentVO();
				studentVO.setSerial_num(resultSet.getInt("serial_num"));
				studentVO.setStudent_num(resultSet.getString("student_num"));
				studentVO.setStudent_name(resultSet.getString("student_name"));
				studentVO.setStudent_id(resultSet.getString("student_id"));
				studentVO.setStudent_pass(resultSet.getString("student_pass"));
				studentVO.setMajor_code(resultSet.getString("major_code"));
				studentVO.setStudent_bday(resultSet.getString("student_bday"));
				studentVO.setStudent_phone(resultSet.getString("student_phone"));
				studentVO.setStudent_address(resultSet.getString("student_address"));
				studentVO.setStudent_email(resultSet.getString("student_email"));
				studentVO.setStudent_date(resultSet.getDate("student_date"));
				// print info for each student
				System.out.printf("[Serial Number]: %d, [Student Number]: %s, [Name]: %s,%n",
						studentVO.getSerial_num(), studentVO.getStudent_num(), studentVO.getStudent_name());
				System.out.printf("[Student ID]: %s, [Student Major Code]: %s, [Student Birthday]: %s,%n",
						studentVO.getStudent_id(), studentVO.getMajor_code(), studentVO.getStudent_bday());
				System.out.printf("[Student Phone Number]: %s. [Student Address]: %s,%n",
						studentVO.getStudent_phone(), studentVO.getStudent_address());
				System.out.printf("[Student Email]: %s, [Student Date]: %s%n",
						studentVO.getStudent_email(), studentVO.getStudent_date());
				System.out.println("---------------------------------------------------------------------------");
			}
		} catch(SQLException e) {
			System.out.println("SQL Error");
		}
	}

	public boolean studentLogin(String username, String password) throws Exception {
		String selectStatement = "SELECT * FROM student WHERE student_id = ? AND student_pass = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean checkLogin = false;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			// if resultSet has a tuple matching the id/pw, login worked 
			if(resultSet.next()) {
				checkLogin = true;
			}
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} catch(Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
		return checkLogin;
	}
	
	public String getStudentNumber(String username, String password) throws Exception {
		String selectStatement = "SELECT student_num FROM student WHERE student_id = ? AND student_pass = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// so it does not return null
		String student_num = "";
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			// saves student number of id/pw from database into student_num
			if(resultSet.next()) {
				student_num = resultSet.getString("student_num");
			}
		} catch(SQLException e) {
			System.out.println("SQL Error");
		} catch(Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
		return student_num;
	}
}
