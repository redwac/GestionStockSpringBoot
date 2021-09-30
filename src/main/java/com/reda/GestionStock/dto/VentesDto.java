package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.LigneVente;
import com.reda.GestionStock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commantaire;

    private Integer idEntreprise;

    private List<LigneVentDto> ligneVents;

    public static VentesDto fromEntity(Ventes ventes){
        if (ventes == null){
            return null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commantaire(ventes.getCommantaire())
                .idEntreprise(ventes.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto){
        if (ventesDto == null){
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setCode(ventes.getCode());
        ventes.setDateVente(ventes.getDateVente());
        ventes.setCommantaire(ventes.getCommantaire());
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());

        return ventes;
    }
}
