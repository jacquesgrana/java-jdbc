package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao{

	private static final String CL_URL;
	private static final String CL_USER;
	private static final String CL_PW;
	
	private static final String REQ_SELECT_FOU = "SELECT * FROM FOURNISSEUR;";
	
	static {

		ResourceBundle bundleCloud = ResourceBundle.getBundle("cloud");
		CL_URL = bundleCloud.getString("cloud.url");
		CL_USER = bundleCloud.getString("cloud.user");
		CL_PW = bundleCloud.getString("cloud.pw");
	}
	
	@Override
	public List<Fournisseur> extraire() {
		
		List<Fournisseur> fournisseurs = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(REQ_SELECT_FOU)) {
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String nom = rs.getString("NOM");
				Fournisseur fou = new Fournisseur(id, nom);
				fournisseurs.add(fou);
			}
			if (fournisseurs.size() > 0) {
				System.out.println("chargement de la liste ok");
			}
			else {
				System.out.println("chargement de la liste ko");
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
	public void insert(Fournisseur fournisseur) {
		//int result = 0;
		StringBuilder buider = new StringBuilder();
		String req = buider.append("INSERT INTO FOURNISSEUR VALUES (").append(fournisseur.getId()).append(", '").append(fournisseur.getNom()).append("');").toString();
		int result = connectAndExecUpdate(req);
		
		if(result == 1) {
			System.out.println("Insertion ok");
		}
		else {
			System.out.println("Insertion ko");
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		//int result = 0;
		StringBuilder buider = new StringBuilder();
		String req = buider.append("UPDATE FOURNISSEUR SET NOM='").append(nouveauNom).append("' WHERE NOM='").append(ancienNom).append("';").toString();
		int result = connectAndExecUpdate(req);
		
		if(result >= 1) {
			System.out.println("Update ok");
		}
		else {
			System.out.println("Update ko");
		}
		return result;
	}


	@Override
	public boolean delete(Fournisseur fournisseur) {
		StringBuilder buider = new StringBuilder();
		String req = buider.append("DELETE FROM FOURNISSEUR WHERE NOM='").append(fournisseur.getNom()).append("' AND ID=").append(fournisseur.getId()).append(";").toString();
		int result = connectAndExecUpdate(req);
		
		if(result == 1) {
			System.out.println("Delete ok");
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
	private int connectAndExecUpdate(String req) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(CL_URL, CL_USER, CL_PW);
				Statement st = connection.createStatement()) {
			result = st.executeUpdate(req);			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		return result;
	}

}
