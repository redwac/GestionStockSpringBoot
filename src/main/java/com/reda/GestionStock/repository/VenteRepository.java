package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.CommandeClient;
import com.reda.GestionStock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findVenteByCode(String code);
}
