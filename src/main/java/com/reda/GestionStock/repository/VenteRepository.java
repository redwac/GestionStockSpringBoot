package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Ventes, Integer> {
}
