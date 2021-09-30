package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null){
            errors.add("Veuiller renseigner le nom d'utilisateur' ");
            errors.add("Veuiller renseigner le prenom d'utilisateur' ");
            errors.add("Veuiller renseigner le mot de passe d'utilisateur' ");
            errors.add("Veuillez renseigner l'ardresse d'utilisateur ");
            errors.add("Veuiller renseigner l'email d'utilisateur' ");

            return errors;
        }

        if(!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuiller renseigner le nom d'utilisateur' ");
        }
        if(!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuiller renseigner le prenom d'utilisateur' ");
        }
        if(!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veuiller renseigner l'email d'utilisateur' ");
        }
        if(!StringUtils.hasLength(utilisateurDto.getMotDePass())){
            errors.add("Veuiller renseigner le mot de passe d'utilisateur' ");
        }
        if (utilisateurDto.getDateNaissance()==null){
            errors.add("Veuillez renseigner la date naissance");
        }
        if (utilisateurDto.getAdress()==null){
            errors.add("Veuillez renseigner l'ardresse d'utilisateur ");
        }else {
            if(!StringUtils.hasLength(utilisateurDto.getAdress().getAdresse1())){
                errors.add("Le champ Adresse 1 est obligatoir ");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdress().getVille())){
                errors.add("Le champ Cille est obligatoir ");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdress().getCodePostal())){
                errors.add("Le champ code Postal est obligatoir ");
            }
            if(!StringUtils.hasLength(utilisateurDto.getAdress().getPays())){
                errors.add("Le champ pays est obligatoir ");
            }
        }

        return errors;
    }
}
