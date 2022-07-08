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
	
	private static final String REQ_AFF_ART = "SELECT * FROM ARTICLE";
	private static final String REQ_AFF_FOU = "SELECT * FROM FOURNISSEUR";
	
	public static void main(String[] args) {
		
		try {
			Connection connection1 = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Connection ok !\n");
			
			PreparedStatement statement1 = connection1.prepareStatement(REQ_AFF_ART);
			ResultSet resultSet1 = statement1.executeQuery();
			
			while (resultSet1.next()) {
				String id = resultSet1.getString(1);
				String design = resultSet1.getString(3);
				Double prix = resultSet1.getDouble(4);
				
				System.out.println(id + " / " + design + " / " + prix);
			}
			//connection1.close();
			
			
			PreparedStatement statement2 = connection1.prepareStatement(REQ_AFF_FOU);
			ResultSet resultSet2 = statement2.executeQuery();
			
			System.out.println("\n");
			while (resultSet2.next()) {
				String id = resultSet2.getString(1);
				String nom = resultSet2.getString(2);
				
				System.out.println(id + " / " + nom);
			}
			
			connection1.close();
		} 
		catch (SQLException e) {
			System.out.println("Connection ko !");
		}
		
	}

}
