package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.EntrepriseApi;
import com.reda.GestionStock.dto.EntrepriseDto;
import com.reda.GestionStock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findByID(Integer idEntreprise) {
        return entrepriseService.findByID(idEntreprise);
    }

    @Override
    public List<EntrepriseDto> finAll() {
        return entrepriseService.finAll();
    }

    @Override
    public void delete(Integer idEntreprise) {
        entrepriseService.delete(idEntreprise);
    }
}
