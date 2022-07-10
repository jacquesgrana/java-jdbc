package jdbc;

import java.util.ArrayList;
import java.util.List;

import entites.Article;
import entites.Fournisseur;
import jdbc.dao.ArticleDaoPrepStat;
import jdbc.dao.FournisseurDaoPrepStat;

public class TestJdbcArticles {

	public static void main(String[] args) {
		List<Article> articles = new ArrayList<>();
		List<Fournisseur> fournisseurs = new ArrayList<>();
		ArticleDaoPrepStat daoArt = new ArticleDaoPrepStat();
		articles = daoArt.extraire();
		displayListArt(articles);
		
		FournisseurDaoPrepStat daoFou = new FournisseurDaoPrepStat();
		int id = 4;
		String nom = "La Maison de la Peinture";
		Fournisseur fou = new Fournisseur(id, nom);
		daoFou.insert(fou);
		fournisseurs = daoFou.extraire();
		displayListFou(fournisseurs);
		
		daoArt.insert(new Article((Integer) (11),"M01", "Peinture blanche 1L", 12.5f, fou));
		daoArt.insert(new Article((Integer) (12),"M02", "Peinture rouge mate 1L", 15.5f, fou));
		daoArt.insert(new Article((Integer) (13),"M03", "Peinture noire laquée 1L", 17.8f, fou));
		daoArt.insert(new Article((Integer) (14),"M04", "Peinture bleue mate 1L", 15.5f, fou));
		articles = daoArt.extraire();
		displayListArt(articles);
		
		// TODO finir ********************************************************************
		
		/*
		int id = 4;
		String nom = "L'espace création";
		Article fou = new Fournisseur(id, nom);
		dao.insert(fou);
		articles = dao.extraire();
		displayList(articles);
		
		String nouveauNom = "Monoprix";
		dao.update(nom, nouveauNom);
		articles = dao.extraire();
		displayList(articles);
		
		fou.setNom(nouveauNom);
		dao.delete(fou);
		articles = dao.extraire();
		displayList(articles);
		*/
	}

	private static void displayListArt(List<Article> articles) {
		if (articles.size() > 0) {
			System.out.println("\nListe des articles : \n");
			for(Article art : articles) {
				System.out.println(art.toString());
			}
			System.out.println();
		}
		
	}
	
	private static void displayListFou(List<Fournisseur> fournisseurs) {
		if (fournisseurs.size() > 0) {
			System.out.println("\nListe des fournisseurs : \n");
			for(Fournisseur fou : fournisseurs) {
				System.out.println(fou.toString());
			}
			System.out.println();
		}
		
	}

}
