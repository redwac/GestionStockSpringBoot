package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.FournisseurAPI;
import com.reda.GestionStock.dto.FournisseurDto;
import com.reda.GestionStock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurAPI {


    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findByID(Integer idFournisseur) {
        return fournisseurService.findByID(idFournisseur);
    }

    @Override
    public List<FournisseurDto> finAll() {
        return fournisseurService.finAll();
    }

    @Override
    public void delete(Integer idFournisseur) {
        fournisseurService.delete(idFournisseur);
    }
}
