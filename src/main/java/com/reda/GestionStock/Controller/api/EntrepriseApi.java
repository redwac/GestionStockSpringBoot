package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.ENTREPRISE_ENDPOINT;

@Api(ENTREPRISE_ENDPOINT)
public interface EntrepriseApi {

    @PostMapping(ENTREPRISE_ENDPOINT + "/create")
    EntrepriseDto save(@RequestBody EntrepriseDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    EntrepriseDto findByID(@PathVariable Integer idEntreprise);

    @GetMapping(ENTREPRISE_ENDPOINT + "/all")
    List<EntrepriseDto> finAll();

    @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
    void delete(@PathVariable Integer idEntreprise);
}
