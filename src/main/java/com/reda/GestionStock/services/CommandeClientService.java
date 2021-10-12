package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    CommandeClientDto findByID(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> finAll();

    void delete(Integer id);

}
