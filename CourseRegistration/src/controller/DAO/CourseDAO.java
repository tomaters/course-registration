package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DBUtil;
import model.CourseVO;

public class CourseDAO {
	public void viewCourses() throws Exception {
		String selectStatement = "SELECT * FROM course ORDER BY serial_num";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CourseVO courseVO = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			System.out.printf("Serial num\tCourse ABV\tCourse Name%n");
			while (resultSet.next()) {
				courseVO = new CourseVO();
				courseVO.setSerial_num(resultSet.getInt("serial_num"));;
				courseVO.setCourse_abv(resultSet.getString("course_abv"));
				courseVO.setCourse_name(resultSet.getString("course_name"));;
				System.out.printf("%d\t%s\t%s%n", courseVO.getSerial_num(), courseVO.getCourse_abv(), courseVO.getCourse_name());
			}
		} catch (SQLException e) {
			System.out.println("SQL Error");
		} catch (Exception e) {
			System.out.println("Java error");
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

	public void addCourse(CourseVO courseVO) throws Exception {
		String insertStatement = "INSERT INTO course VALUES (course_seq.nextval, ?, ?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, courseVO.getCourse_abv());
			preparedStatement.setString(2, courseVO.getCourse_name());
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.println(courseVO.getCourse_name() + " course added successfully");
			} else {
				System.out.println("Course failed to add");
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

	public boolean editCourse(CourseVO courseVO) throws Exception {
		String updateStatement = "UPDATE course SET course_abv = ?, course_name = ? WHERE serial_num = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean updateFlag = false;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, courseVO.getCourse_abv());
			preparedStatement.setString(2, courseVO.getCourse_name());
			preparedStatement.setInt(3, courseVO.getSerial_num());
			int result = preparedStatement.executeUpdate();

			if (result == 1) {
				System.out.println(courseVO.getCourse_name() + " course update successful");
				updateFlag = true;
			} else {
				System.out.println("Course update failed. try again");
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
		return updateFlag;
	}

	public void deleteCourse(int serial_num) throws Exception {
		String deleteStatement = "DELETE FROM course WHERE serial_num = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, serial_num);
			int result = preparedStatement.executeUpdate();
			if (result == 1) {
				System.out.printf("Course %d deleted successfully%n", serial_num);
			} else {
				System.out.println("Course failed to delete. Try again");
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
}
