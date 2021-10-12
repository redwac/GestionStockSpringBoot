package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.ArticleApi;
import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class ArticleController implements ArticleApi {


    private ArticleService articleService;

    @Autowired    // permet d'injecter ou cree automatiquement des instances 
    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto);
    }

    @Override
    public Article findByID(Integer id) {
        return articleService.findByID(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> finAll() {
        return articleService.finAll();
    }

    @Override
    public void delete(Integer id) {

    }
}
