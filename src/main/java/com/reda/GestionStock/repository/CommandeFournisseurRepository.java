package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.CommandeClient;
import com.reda.GestionStock.model.CommandeFournissuer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournissuer, Integer> {

    Optional<CommandeFournissuer> findCommandeFournisseurByCode(String code);


}
