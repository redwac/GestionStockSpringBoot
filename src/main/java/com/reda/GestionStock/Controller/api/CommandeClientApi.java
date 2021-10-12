package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclients")
public interface CommandeClientApi {

    // au lieu d'utiliser les Entity DTO on va utiliser le rapport Spring_Boot qui est Response Entity (c'est optionel)

    @PostMapping(APP_ROOT + "/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto); // les DTOsont les intermediaire entre les notre api les l'exterieur

    @GetMapping(APP_ROOT + "/commandesclient/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findByID(@PathVariable Integer idCommandeClient);

    @GetMapping(APP_ROOT + "/commandesclient/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(APP_ROOT + "/commandesclient/all")
    ResponseEntity<List<CommandeClientDto>> finAll();

    @DeleteMapping(APP_ROOT + "/commandesclient/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);

}
