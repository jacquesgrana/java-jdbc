package jdbc.dao;

import java.util.List;

import entites.Article;

public interface ArticleDao {
	
	List<Article> extraire();

	void insert(Article article);

	int update(String ancienNom, String nouveauNom);

	boolean delete(Article article);
}