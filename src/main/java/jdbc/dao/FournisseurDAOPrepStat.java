package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entites.Fournisseur;

public class FournisseurDAOPrepStat implements FournisseurDao{

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	
	private static final String REQ_SELECT_FOU = "SELECT * FROM FOURNISSEUR;";
	private static final String REQ_INSERT_FOU = "INSERT INTO FOURNISSEUR VALUES (?, ?);";
	private static final String REQ_UPDATE_FOU = "UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?;";
	private static final String REQ_DELETE_FOU = "DELETE FROM FOURNISSEUR WHERE ID = ? AND NOM = ?;";
	
	static {

		ResourceBundle bundle = ResourceBundle.getBundle("cloud");
		DB_URL = bundle.getString("database.url");
		DB_USER = bundle.getString("database.user");
		DB_PW = bundle.getString("database.pw");
	}
	
	@Override
	public List<Fournisseur> extraire() {
		
		List<Fournisseur> fournisseurs = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement st = connection.prepareStatement(REQ_SELECT_FOU);
			ResultSet rs = st.executeQuery()
		){
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String nom = rs.getString("NOM");
				Fournisseur fou = new Fournisseur(id, nom);
				fournisseurs.add(fou);
			}
			if (fournisseurs.size() > 0) {
				System.out.println("\nchargement de la liste ok");
			}
			else {
				System.out.println("\nchargement de la liste ko");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(fournisseurs.size() > 0) {
			return fournisseurs;
		}
		return null;
	}

	@Override
	public void insert(Fournisseur fou) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_INSERT_FOU);
			){
			prepStat.setInt(1, fou.getId());
			prepStat.setString(2, fou.getNom());
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result == 1) {
			System.out.println("\n\nInsertion ok");
		}
		else {
			System.out.println("Insertion ko");
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_UPDATE_FOU);
			){
			prepStat.setString(1, nouveauNom);
			prepStat.setString(2, ancienNom);
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result >= 1) {
			System.out.println("\n\nUpdate ok");
		}
		else {
			System.out.println("\n\nUpdate ko");
		}
		return result;
	}


	@Override
	public boolean delete(Fournisseur fou) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
				PreparedStatement prepStat = connection.prepareStatement(REQ_DELETE_FOU);
				){
				prepStat.setInt(1, fou.getId());
				prepStat.setString(2, fou.getNom());
				result = prepStat.executeUpdate();			
			} 
			catch (Exception e) {
				System.out.println("Connection cloud ko ! : " + e.getMessage());
			}
		
		if(result == 1) {
			System.out.println("\n\nDelete ok");
			return true;
		}
		else {
			System.out.println("Delete ko");
			return false;
		}
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	/*
	private int connectAndExecUpdate(String req) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement()
						//PreparedStatement st = connection.prepareStatement(REQ_SELECT_FOU)
						){
			result = st.executeUpdate(req);			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		return result;
	}*/

}
