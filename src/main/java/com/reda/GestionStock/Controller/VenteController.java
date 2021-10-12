package com.reda.GestionStock.Controller;

import com.reda.GestionStock.Controller.api.VenteAPI;
import com.reda.GestionStock.dto.VentesDto;
import com.reda.GestionStock.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteAPI {

    private VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return venteService.save(dto);
    }

    @Override
    public VentesDto findByID(Integer id) {
        return venteService.findByID(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public List<VentesDto> finAll() {
        return venteService.finAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
