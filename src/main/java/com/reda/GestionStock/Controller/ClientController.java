package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.ClientApi;
import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findByID(Integer id) {
        return clientService.findByID(id);
    }

    @Override
    public List<ClientDto> finAll() {
        return clientService.finAll();
    }

    @Override
    public void delete(Integer id) {
        clientService.delete(id);
    }
}
