package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.MvStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvStkRepository extends JpaRepository<MvStk, Integer> {
}
