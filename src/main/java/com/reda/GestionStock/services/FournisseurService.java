package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.FournisseurDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    FournisseurDto findByID(Integer id);

    List<FournisseurDto> finAll();

    void delete(Integer id);
}
