package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entites.Article;
import entites.Fournisseur;

public class ArticleDaoPrepStat implements ArticleDao{

	private static final String DB_URL;
	private static final String DB_USER;
	private static final String DB_PW;
	
	private static final String REQ_SELECT_ART = "SELECT a.ID, a.REF, a.DESIGNATION, a.PRIX, a.ID_FOU, f.NOM AS NOM_FOU FROM ARTICLE a, FOURNISSEUR f WHERE a.ID_FOU = f.ID ORDER BY a.ID;";
	private static final String REQ_INSERT_ART = "INSERT INTO ARTICLE VALUES (?, ?, ?, ?, ?);";
	private static final String REQ_UPDATE_ART = "UPDATE ARTICLE SET NOM = ? WHERE NOM = ?;";
	private static final String REQ_DELETE_ART = "DELETE FROM ARTICLE WHERE ID = ?;";
	
	private static final String REQ_SELECT_FOU_BY_ID = "SELECT NOM FROM FOURNISSEUR WHERE ID = ?;";
	private static final String REQ_UPDATE_ART_PRIX_PEINTURE_MATE = "UPDATE ARTICLE SET PRIX = CAST(PRIX * 0.75 AS DECIMAL(7,2)) WHERE UPPER(DESIGNATION) LIKE '%PEINTURE%MATE%';";
	
	static {

		ResourceBundle bundle = ResourceBundle.getBundle("cloud");
		DB_URL = bundle.getString("database.url");
		DB_USER = bundle.getString("database.user");
		DB_PW = bundle.getString("database.pw");
	}
	
	@Override
	public List<Article> extraire() {
		
		List<Article> articles = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement st = connection.prepareStatement(REQ_SELECT_ART);
			ResultSet rs = st.executeQuery()
		){
			while (rs.next()) {
				Integer id = rs.getInt("ID");
				String ref = rs.getString("REF");
				String design = rs.getString("DESIGNATION");
				Float prix = rs.getFloat("PRIX");
				Integer idFou = rs.getInt("ID_FOU");
				String nomFou = rs.getNString("NOM_FOU");
				//Fournisseur fou = new Fournisseur(id, nom);
				Fournisseur fou = new Fournisseur(idFou, nomFou);
				Article art = new Article(id, ref, design, prix, fou);
				articles.add(art);
			}
			
			
			
			
			if (articles.size() > 0) {
				System.out.println("\nchargement de la liste ok");
			}
			else {
				System.out.println("\nchargement de la liste ko");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(articles.size() > 0) {
			return articles;
		}
		return null;
	}

	@Override
	public void insert(Article art) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_INSERT_ART);
			){
			prepStat.setInt(1, art.getId());
			prepStat.setString(2, art.getRef());
			prepStat.setString(3, art.getDesign());
			prepStat.setFloat(4, art.getPrix());
			prepStat.setInt(5, art.getFou().getId());
			
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result == 1) {
			System.out.println("\n\nInsertion article ok");
		}
		else {
			System.out.println("Insertion article ko");
		}
		
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Article article) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
/*
	@Override
	public void insert(Article art) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_INSERT_ART);
			){
			prepStat.setInt(1, art.getId());
			prepStat.setString(2, art.getNom());
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
			PreparedStatement prepStat = connection.prepareStatement(REQ_UPDATE_ART);
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
	public boolean delete(Article art) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
				PreparedStatement prepStat = connection.prepareStatement(REQ_DELETE_ART);
				){
				prepStat.setInt(1, art.getId());
				prepStat.setString(2, art.getNom());
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
	}*/

}
