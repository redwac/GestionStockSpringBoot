package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    ArticleDto findByID(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> finAll();

    void delete(Integer id);
}
