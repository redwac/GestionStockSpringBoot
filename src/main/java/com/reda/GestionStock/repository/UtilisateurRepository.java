package com.reda.GestionStock.repository;

import com.reda.GestionStock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    //JPL query n'est pas obligatoir juste pour l'utiliser dans cette exp
    @Query(value = " select u from utilisateur u where u.email= :email")
    Optional<Utilisateur> findUtilisateurBuEmail(String email);


}
