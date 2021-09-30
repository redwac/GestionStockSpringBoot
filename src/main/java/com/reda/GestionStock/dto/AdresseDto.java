package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.Adress;
import com.reda.GestionStock.model.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
@Data
@Builder
public class AdresseDto {

    private Integer id;

    private String adresse1;

    private String adresse2;

    private String ville;

    private String codePostal;

    private String pays;

    public static AdresseDto fromEntity(Adress adress){
        if (adress == null){
            return null;
        }
        // j'arrive pas a utiliset le getID de la classe article probleme annotation @EqualsAndHashCode(callSuper = true)
        return AdresseDto.builder()
                .adresse1(adress.getAdresse1())
                .adresse2(adress.getAdresse2())
                .ville(adress.getVille())
                .codePostal(adress.getCodePostal())
                .pays(adress.getPays())
                .build();
    }
    public static Adress toEntity(AdresseDto adresseDto){
        if (adresseDto == null){
            return null;
        }
        Adress adress = new Adress();
        adress.setAdresse1(adresseDto.getAdresse1());
        adress.setAdresse2(adresseDto.getAdresse2());
        adress.setVille(adresseDto.getVille());
        adress.setCodePostal(adresseDto.getCodePostal());
        adress.setPays(adresseDto.getPays());

        return adress;
    }

}
