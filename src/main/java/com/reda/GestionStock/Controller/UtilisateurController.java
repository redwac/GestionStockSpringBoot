package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.UtilisateurApi;
import com.reda.GestionStock.dto.UtilisateurDto;
import com.reda.GestionStock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findByID(Integer id) {
        return utilisateurService.findByID(id);
    }

    @Override
    public List<UtilisateurDto> finAll() {
        return utilisateurService.finAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
