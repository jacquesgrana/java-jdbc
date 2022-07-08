package test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunTest01 {

	private static final String DB_URL = "jdbc:mariadb://localhost:3306/compta2";
	private static final String DB_USER = "admin";
	private static final String DB_PW = "Ktoto1956";
	
	private static final String REQ_AFF_ARTICLE = "SELECT * FROM ARTICLE";
	
	public static void main(String[] args) {
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Connection ok !\n");
			
			PreparedStatement statement = connection.prepareStatement(REQ_AFF_ARTICLE);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String id = resultSet.getString(1);
				String design = resultSet.getString(3);
				Double prix = resultSet.getDouble(4);
				
				System.out.println(id + " / " + design + " / " + prix);
			}
			
			connection.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection ko !");
		}
		
	}

}
