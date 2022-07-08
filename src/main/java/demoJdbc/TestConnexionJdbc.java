package demoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbc {

	public static void main(String[] args) {
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		//String driverName = monFichierConf.getString("database.driver");
		String dbUrl = monFichierConf.getString("database.url");
		String dbUser = monFichierConf.getString("database.username");
		String dbPw = monFichierConf.getString("database.password");
		try {
			Connection connection1 = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			System.out.println("Connection ok !\n");
			System.out.println("connection : " + connection1 + "\n");
			connection1.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection ko !");
		}
	}

}
