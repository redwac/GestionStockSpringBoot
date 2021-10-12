package com.reda.GestionStock.services;

import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.dto.VentesDto;

import java.util.List;

public interface VenteService {

    VentesDto save(VentesDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    VentesDto findByID(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> finAll();

    void delete(Integer id);
}
