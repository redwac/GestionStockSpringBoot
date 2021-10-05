package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {


}
