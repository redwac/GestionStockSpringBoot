package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.EntrepriseDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    EntrepriseDto findByID(Integer id);

    List<EntrepriseDto> finAll();

    void delete(Integer id);
}
