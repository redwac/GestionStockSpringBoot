package com.reda.GestionStock.validator;

import com.reda.GestionStock.dto.CategoryDto;
import com.reda.GestionStock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {


    public static List<String> validate(ClientDto clientDto){
        List<String> errors = new ArrayList<>();

        if (clientDto == null){
            errors.add("Veuiller renseigner le nom du client ");
            errors.add("Veuiller renseigner le prenom du client ");
            errors.add("Veuiller renseigner l'ardress du client ");
            errors.add("Veuiller renseigner l'email' du client ");
            errors.add("Veuiller renseigner le numero telephone du client ");

            return errors;
        }

        if(!StringUtils.hasLength(clientDto.getNom())){
            errors.add("Veuiller renseigner le nom du client ");
        }
        if(!StringUtils.hasLength(clientDto.getPrenom())){
            errors.add("Veuiller renseigner le prenom du client ");
        }
        if(clientDto.getAdress()==null){
            errors.add("Veuiller renseigner l'ardress du client ");
        }else {
            if(!StringUtils.hasLength(clientDto.getAdress().getAdresse1())){
                errors.add("Veuiller renseigner l'adresse 1 du client ");
            }
            if(!StringUtils.hasLength(clientDto.getAdress().getVille())){
                errors.add("Veuiller renseigner la ville du client ");
            }
            if(!StringUtils.hasLength(clientDto.getAdress().getCodePostal())){
                errors.add("Veuiller renseigner le code postal du client ");
            }
            if(!StringUtils.hasLength(clientDto.getAdress().getPays())){
                errors.add("Veuiller renseigner le pay du client ");
            }
        }
        if(!StringUtils.hasLength(clientDto.getMail())){
            errors.add("Veuiller renseigner l'email' du client ");
        }
        if(!StringUtils.hasLength(clientDto.getNumTel())){
            errors.add("Veuiller renseigner le numero telephone du client ");
        }
        return errors;
    }
}
