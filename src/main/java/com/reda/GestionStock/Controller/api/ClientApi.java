package com.reda.GestionStock.Controller.api;

import com.reda.GestionStock.dto.ClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.reda.GestionStock.Utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/client") // Api pour Swagger
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/client/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) // consume = je vai consommer un format JSON ...
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "client/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findByID(@PathVariable("{idClient}") Integer id);

    @GetMapping(value = APP_ROOT + "client/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> finAll();

    @DeleteMapping(value = APP_ROOT + "client/delete/{idclient}")
    void delete(@PathVariable("{idClient}") Integer id);
}
