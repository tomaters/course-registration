package courseRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String username = "hr";
		String password = "hr";
		Connection connection = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("connection complete");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.printf("Error: %s", e);
		}
	}
}
