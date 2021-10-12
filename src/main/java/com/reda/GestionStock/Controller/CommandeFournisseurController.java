package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.CommandeFournisseurApi;
import com.reda.GestionStock.dto.CommandeFournissuerDto;
import com.reda.GestionStock.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {


    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournissuerDto save(CommandeFournissuerDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournissuerDto findByID(Integer idCommandeFournisseur) {
        return commandeFournisseurService.findByID(idCommandeFournisseur);
    }

    @Override
    public CommandeFournissuerDto findByCode(String codeCommandeFournisseur) {
        return commandeFournisseurService.findByCode(codeCommandeFournisseur);
    }

    @Override
    public List<CommandeFournissuerDto> finAll() {
        return commandeFournisseurService.finAll();
    }

    @Override
    public void delete(Integer idCommandeFournisseur) {
        commandeFournisseurService.delete(idCommandeFournisseur);
    }
}
