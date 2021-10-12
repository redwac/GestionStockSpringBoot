package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.VENTE_ENDPOINT;

@Api(VENTE_ENDPOINT)
public interface VenteAPI {

    @PostMapping(VENTE_ENDPOINT + "/create")
    VentesDto save(VentesDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(VENTE_ENDPOINT + "/{idVente}")
    VentesDto findByID(@PathVariable("{idVente}") Integer id);

    @GetMapping(VENTE_ENDPOINT + "/{codeVente}")
    VentesDto findByCode(@PathVariable("{codeVente}") String code);

    @GetMapping(VENTE_ENDPOINT + "/all")
    List<VentesDto> finAll();

    @DeleteMapping(VENTE_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("{idVente}")Integer id);
}
