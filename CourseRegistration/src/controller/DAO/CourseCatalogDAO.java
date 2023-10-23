package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DBUtil;
import model.CourseCatalogVO;

/* Data Access Object (DAO) classes are used to separate code that deals with the database from the rest of the code
    - They provide a layer so the programmer can just work with the database
    - They take all the SQL queries, connections, mapping, etc. and removes it from the main database system code
    - Contain SQL CRUD operations
*/
public class CourseCatalogDAO {
	public void addRegistration(CourseCatalogVO courseCatalogVO) throws Exception {
		String insertStatement = "INSERT into course_catalog VALUES (course_catalog.seq.nextval, ?, ?, ?, sysdate)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, courseCatalogVO.getStudent_num());
			preparedStatement.setString(2, courseCatalogVO.getCourse_abv());
			preparedStatement.setString(3, courseCatalogVO.getCourse_type());
			
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("Course registration complete");
			} else {
				System.out.println("Course registration failed. Try again");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
		} catch (Exception e) {
			System.out.println("Java error");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}

	public void deleteCourse(int serial_num) throws Exception {
		String deleteStatement = "DELETE FROM course_catalog WHERE serial_num = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, serial_num);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println("Course successfully cancelled");
			} else {
				System.out.println("Course failed to cancel. Try again");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
		} catch (Exception e) {
			System.out.println("Java error");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}

	public void viewCourseCatalog(String student_num) throws Exception {
		// inner join statement: view intersection between 'course_catalog' and 'course' where 'course_abv' are the same
		// also between 'course_catalog' and 'student' where 'student_num' are the same; variable is student_number, which is input
		String selectStatement = "SELECT cc.serial_num AS serial_num, cc.student_num AS student_num, cc.course_abv AS course_abv, "
				+ "c.course_name AS course_name, stu.student_name AS student_name, "
				+ "course_type, course_date FROM course_catalog cc, course c, student stu "
				+ "WHERE cc.student_num = ? "
				+ "AND cc.course_abv = c.course_abv "
				+ "AND cc.student_num = stu.student_num "
				+ "ORDER BY course_date";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CourseCatalogVO courseCatalogVO = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, student_num);
			resultSet = preparedStatement.executeQuery();
			System.out.println("Serioal \t학생번호\t\t과목약어\t과목명\t학생이름\t과목구분\t등록일");
			while (resultSet.next()) {
				courseCatalogVO = new CourseCatalogVO();
				courseCatalogVO.setSerial_num(resultSet.getInt("serial_num"));
				courseCatalogVO.setStudent_num(resultSet.getString("student_num"));
				courseCatalogVO.setCourse_abv(resultSet.getString("course_abv"));
				courseCatalogVO.setCourse_type(resultSet.getString("course_type"));
				courseCatalogVO.setReg_date(resultSet.getDate("course_date"));
				System.out.printf("[Serial number]: %d, [Student number]: %s, [Course ABV]: %s,%n", 
						courseCatalogVO.getSerial_num(), courseCatalogVO.getStudent_num(), courseCatalogVO.getCourse_abv());
				System.out.printf("[Course name]: %s, [Student name]: %s, [Course type]: %s, [Registration date]: %s%n"
						, resultSet.getString("course_name"), resultSet.getString("student_name"), courseCatalogVO.getCourse_type(), courseCatalogVO.getReg_date());
			}
		} catch (SQLException s) {
			System.out.println("SQL Error");
		} catch (Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}

	public String getCourseABV(String course_name) throws Exception {
		String course_abv = "";
		String selectStatement = "SELECT course_abv FROM course WHERE course_name = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, course_name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				course_abv = resultSet.getString("course_abv");
			} else {
				System.out.println("Search failed: the course name " + course_name + " does not exist");
			}
		} catch (SQLException s) {
			System.out.println("SQL Error");
		} catch (Exception e) {
			System.out.println("Java Error");
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Error");
			}
		}
		return course_abv;
	}
}