package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.CommandeFournissuerDto;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournissuerDto save(CommandeFournissuerDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    CommandeFournissuerDto findByID(Integer id);

    CommandeFournissuerDto findByCode(String code);

    List<CommandeFournissuerDto> finAll();

    void delete(Integer id);
}
