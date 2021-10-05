package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
