package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ArticleDto;
import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.model.Article;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    ClientDto findByID(Integer id);

    List<ClientDto> finAll();

    void delete(Integer id);
}
