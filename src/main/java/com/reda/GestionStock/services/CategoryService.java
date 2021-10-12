package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    CategoryDto findByID(Integer id);

    CategoryDto findByCode(String code);

    List<CategoryDto> finAll();

    void delete(Integer id);
}
