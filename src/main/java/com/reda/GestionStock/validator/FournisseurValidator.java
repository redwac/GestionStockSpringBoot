package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.ClientDto;
import com.reda.GestionStock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null){
            errors.add("Veuiller renseigner le nom du client ");
            errors.add("Veuiller renseigner le prenom du client ");
            errors.add("Veuiller renseigner l'ardress du client ");
            errors.add("Veuiller renseigner l'email' du client ");
            errors.add("Veuiller renseigner le numero telephone du client ");

            return errors;
        }

        if(!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("Veuiller renseigner le nom du fournisseur ");
        }
        if(!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("Veuiller renseigner le prenom du fournisseur ");
        }
        if(fournisseurDto.getAdress()==null){
            errors.add("Veuiller renseigner l'ardress du fournisseur ");
        }else {
            if(!StringUtils.hasLength(fournisseurDto.getAdress().getAdresse1())){
                errors.add("Veuiller renseigner l'adresse 1 du fournisseur ");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAdress().getVille())){
                errors.add("Veuiller renseigner la ville du fournisseur ");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAdress().getCodePostal())){
                errors.add("Veuiller renseigner le code postal du fournisseur ");
            }
            if(!StringUtils.hasLength(fournisseurDto.getAdress().getPays())){
                errors.add("Veuiller renseigner le pay du fournisseur ");
            }
        }
        if(!StringUtils.hasLength(fournisseurDto.getMail())){
            errors.add("Veuiller renseigner l'email' du fournisseur ");
        }
        if(!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("Veuiller renseigner le numero telephone du fournisseur ");
        }
        return errors;
    }

}
