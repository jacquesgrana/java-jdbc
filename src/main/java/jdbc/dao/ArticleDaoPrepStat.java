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
	private static final String REQ_UPDATE_ART = "UPDATE ARTICLE SET DESIGNATION = ? WHERE DESIGNATION = ?;";
	private static final String REQ_UPDATE_ART_PRIX_PEINTURE_MATE = "UPDATE ARTICLE SET PRIX = CAST(PRIX * 0.75 AS DECIMAL(7,2)) WHERE UPPER(DESIGNATION) LIKE '%PEINTURE%MATE%';";
	private static final String REQ_CALC_MOY_ART_PRIX = "SELECT AVG(PRIX) AS MOY_PRIX FROM ARTICLE";
	private static final String REQ_DELETE_ART_PEINTURE = "DELETE FROM ARTICLE WHERE UPPER(DESIGNATION) LIKE '%PEINTURE%';";
	
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
				Article art = new Article(id, ref, design, prix, new Fournisseur(idFou, nomFou));
				articles.add(art);
			}
			if (articles.size() > 0) {
				System.out.println("\nChargement de la liste des articles ok");
			}
			else {
				System.out.println("\nChargement de la liste des articles ko");
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
	public int update(String ancienneDesign, String nouvelleDesign) {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_UPDATE_ART);
			){
			prepStat.setString(1, nouvelleDesign);
			prepStat.setString(2, ancienneDesign);
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result >= 1) {
			System.out.println("\n\nUpdate article ok");
		}
		else {
			System.out.println("\n\nUpdate article ko");
		}
		return result;
	}
	
	

	@Override
	public boolean delete(Article article) {
		return false;
	}
	
	public Float calcMoyPrix() {
		Float moyPrix = 0f;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
				PreparedStatement st = connection.prepareStatement(REQ_CALC_MOY_ART_PRIX);
				ResultSet rs = st.executeQuery()
			){
				while (rs.next()) {
					moyPrix = rs.getFloat(1);
				}
				return moyPrix;
		}
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		
		return 0f;
	}
	
	public int updatePrixPeintureMate() {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_UPDATE_ART_PRIX_PEINTURE_MATE);
			){
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result >= 1) {
			System.out.println("\n\nUpdate prix des articles ok");
		}
		else {
			System.out.println("\n\nUpdate prix des articles ko");
		}
		return result;
	}
	
	public boolean deletePeinture() {
		int result = 0;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			PreparedStatement prepStat = connection.prepareStatement(REQ_DELETE_ART_PEINTURE);
			){
			result = prepStat.executeUpdate();			
		} 
		catch (Exception e) {
			System.out.println("Connection cloud ko ! : " + e.getMessage());
		}
		
		if(result >= 1) {
			System.out.println("\n\nDelete articles peinture ok");
			return true;
		}
		else {
			System.out.println("\n\nDelete articles peinture ko");
			return false;
		}
	}
}
