package com.reda.GestionStock.Controller.api;


import com.reda.GestionStock.dto.FournisseurDto;
import com.reda.GestionStock.model.Fournisseur;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(FOURNISSEUR_ENDPOINT)
public interface FournisseurAPI {

    @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
    FournisseurDto save(@RequestBody FournisseurDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    FournisseurDto findByID(@PathVariable Integer idFournisseur);

    @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
    List<FournisseurDto> finAll();

    @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    void delete(@PathVariable Integer idFournisseur);
}
