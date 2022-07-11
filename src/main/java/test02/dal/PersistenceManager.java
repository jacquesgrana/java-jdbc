package test02.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PersistenceManager {
	/*
	enum StoreMode {
		JBDC, XML;
	}*/

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	private static final String STORE_MODE;
	
	private static Connection jbdcConnection;
	// ajouter strore_mode
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("cloud");
		STORE_MODE = bundle.getString("store.mode");
		DB_URL = bundle.getString("database.url");
		DB_USER = bundle.getString("database.user");
		DB_PW = bundle.getString("database.pw");
	}
	
	private PersistenceManager(){}
	
	
	public static Connection getJDBCConnection() throws SQLException {
		if (jbdcConnection == null || !jbdcConnection.isValid(2)) { //!jbdcConnection.isValid(2)
			
			System.out.println("Nouvelle connection");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		}
		else {
			System.out.println("Retour connection");
			return jbdcConnection;
		}
	}
	
	public static void closeJDBCConnection() throws SQLException {
		if (jbdcConnection != null && !jbdcConnection.isClosed()) {
			jbdcConnection.close();
			System.out.println("Connection fermée");
		}
		else {
			System.out.println("Connection déjà fermée");
		}
	}
	
	public static IFournisseurDAO getFournisseurDAO() {
		//System.out.println("Store mode : " + STORE_MODE);
		switch (STORE_MODE) {
		case "JDBC":
			System.out.println("creation dao");
			return new FournisseurJDBCDAO();
			
		case "XML" :
			return null;
			//break;
		}
		return null;
	}
}
