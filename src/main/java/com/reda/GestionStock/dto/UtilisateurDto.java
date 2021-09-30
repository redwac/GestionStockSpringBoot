package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.Adress;
import com.reda.GestionStock.model.Entreprise;
import com.reda.GestionStock.model.Role;
import com.reda.GestionStock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateNaissance;

    private String motDePass;

    private AdresseDto adress;

    private String photo;

    private EntrepriseDto entreprise;

    private List<RoleDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if (utilisateur == null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateNaissance(utilisateur.getDateNaissance())
                .motDePass(utilisateur.getMotDePass())
                .photo(utilisateur.getPhoto())
                .adress(AdresseDto.fromEntity(utilisateur.getAdress()))
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto == null){
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
        utilisateur.setMotDePass(utilisateurDto.getMotDePass());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setAdress(AdresseDto.toEntity(utilisateurDto.getAdress()));
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));

        return utilisateur;
    }
}
