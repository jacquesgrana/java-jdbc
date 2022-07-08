package demoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	private static final String DB_URL = "jdbc:mariadb://localhost:3306/compta";
	private static final String DB_USER = "admin";
	private static final String DB_PW = "Ktoto1956";
	
	public static void main(String[] args) {
		
		try {
			Connection connection1 = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Connection ok !\n");
			System.out.println("connection : " + connection1 + "\n");
			connection1.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection ko !");
		}

	}

}
