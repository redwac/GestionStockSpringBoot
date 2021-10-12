package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.CommandeFournissuerDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.*;

@Api(COMMANDEFOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDEFOURNISSEUR_ENDPOINT + "/create" )
    CommandeFournissuerDto save(@RequestBody CommandeFournissuerDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(COMMANDEFOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournissuerDto findByID(@PathVariable Integer idCommandeFournisseur);

    @GetMapping(COMMANDEFOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur}")
    CommandeFournissuerDto findByCode(@PathVariable String codeCommandeFournisseur);

    @GetMapping(COMMANDEFOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournissuerDto> finAll();

    @DeleteMapping(COMMANDEFOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable Integer idCommandeFournisseur);

}
