package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.Adress;
import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.model.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;


    private String designation;


    private BigDecimal prixUniteHt;


    private BigDecimal tauxTva;


    private BigDecimal prixUniteTtc;


    private String photo;


    private CategoryDto category;


    private Integer idEntreprise;

    public static ArticleDto fromEntity(Article article){
        if (article == null){
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUniteHt(article.getPrixUniteHt())
                .tauxTva(article.getTauxTva())
                .prixUniteTtc(article.getPrixUniteTtc())
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                //.category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }
    public static Article toEntity(ArticleDto articleDto){
        if (articleDto == null){
            return null;
        }
        Article article = new Article();
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUniteHt(articleDto.getPrixUniteHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUniteTtc(articleDto.getPrixUniteTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));


        return article;
    }
}
