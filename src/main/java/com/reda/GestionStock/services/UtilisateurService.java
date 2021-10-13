package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.UtilisateurDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    UtilisateurDto findByID(Integer id);

    List<UtilisateurDto> finAll();

    UtilisateurDto findUtilisateurByEmail(String email);

    void delete(Integer id);
}
