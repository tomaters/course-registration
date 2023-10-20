package controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DBUtil;
import model.MajorVO;

public class MajorDAO {

	public void viewMajorInfo() {
		String selectStatement = "SELECT * FROM major ORDER BY serial_num";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MajorVO majorVO = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("Serial number\tMajor code\tMajor name");
			
			while(resultSet.next()) {
				majorVO = new MajorVO();
				majorVO.setSerial_num(resultSet.getInt("serial_num"));
				majorVO.setMajor_code(resultSet.getString("major_code"));
				majorVO.setMajor_name(resultSet.getString("major_name"));	
				
				System.out.printf("%d\t\t%s\t\t%s%n", majorVO.getSerial_num(), majorVO.getMajor_code(), majorVO.getMajor_name());
			}
		} catch(SQLException e) { System.out.println("SQL Error"); 
		} catch(Exception e) { System.out.println("Java Error"); 
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void addMajorInfo(MajorVO majorVO) throws Exception {
		String insertStatement = "INSERT INTO major VALUES (major_seq.nextval, ?, ?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, majorVO.getMajor_code());
			preparedStatement.setString(2, majorVO.getMajor_name());
			
			int result = preparedStatement.executeUpdate();
			
			if(result == 1) {
				System.out.println(majorVO.getMajor_name() + " Major successfully added");
			} else {
				System.out.println(majorVO.getMajor_name() + "Major failed to add");
			}
		} catch(SQLException e) { System.out.println("SQL Error");
		e.printStackTrace();
		} catch(Exception e) { System.out.println("Java Error"); 
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void editMajorInfo(MajorVO majorVO) {
		String updateStatement = "UPDATE major SET major_code = ?, major_name = ? WHERE serial_num = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, majorVO.getMajor_code());
			preparedStatement.setString(2, majorVO.getMajor_name());
			preparedStatement.setInt(3, majorVO.getSerial_num());
			
			int result = preparedStatement.executeUpdate();
			
			if(result == 1) {
				System.out.println(majorVO.getMajor_name() + " Major successfully edited");
			} else {
				System.out.println(majorVO.getMajor_name() + "Major failed to edit");
			}
		}catch(SQLException e) { System.out.println("SQL Error"); 
		} catch(Exception e) { System.out.println("Java Error"); 
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void deleteMajorInfo(int serial_num) {
		String deleteStatement = "DELETE FROM major WHERE serial_num = ?";
		Connection connection = null;
		PreparedStatement  preparedStatement = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, serial_num);
			
			int result = preparedStatement.executeUpdate();
			
			if(result == 1) {
				System.out.println("Major successfully deleted");
			} else {
				System.out.println("Major failed to delete");
			}
		}catch(SQLException e) { System.out.println("SQL Error"); 
		} catch(Exception e) { System.out.println("Java Error"); 
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
	
	public void displayListOfMajors() {
		String selectStatement = "SELECT major_code, major_name FROM major ORDER BY serial_num";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MajorVO majorVO = null;
		
		try {
			connection = DBUtil.makeConnection();
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			// display list of majors and their codes
			System.out.println("Major code\tMajor name");
			
			while(resultSet.next()) {
				String major_code = resultSet.getString("major_code");
				String major_name = resultSet.getString("major_name");
				System.out.printf("%s\t\t%s%n", major_code, major_name);
			}
			
		} catch(SQLException e) { System.out.println("SQL Error"); 
		} catch(Exception e) { System.out.println("Java Error"); 
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				System.out.println("SQL Error");
			}
		}
	}
}
