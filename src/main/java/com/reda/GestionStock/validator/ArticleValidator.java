package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {


    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("Veuiller renseigner le code d'article' ");
            errors.add("Veuiller renseigner la designation d'article' ");
            errors.add("Veuiller renseigner le prix HT d'article' ");
            errors.add("Veuiller renseigner le taux TVA d'article' ");
            errors.add("Veuiller renseigner le prix TTC d'article' ");
            errors.add("Veuillez selectionner une category");

            return errors;
        }
        if(!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veuiller renseigner le code d'article' ");
        }
        if(!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuiller renseigner la designation d'article' ");
        }
        if(articleDto.getPrixUniteHt() == null){
            errors.add("Veuiller renseigner le prix HT d'article' ");
        }
        if(articleDto.getTauxTva() == null){
            errors.add("Veuiller renseigner le taux TVA d'article' ");
        }
        if(articleDto.getPrixUniteTtc() == null){
            errors.add("Veuiller renseigner le prix TTC d'article' ");
        }
        if (articleDto.getCategory() == null){
            errors.add("Veuillez selectionner une category");
        }

        return errors;
    }
}
