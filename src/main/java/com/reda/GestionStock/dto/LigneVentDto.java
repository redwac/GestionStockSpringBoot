package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.LigneVente;
import com.reda.GestionStock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVentDto {

    private Integer id;

    private VentesDto ventes;

    private ArticleDto articleDto;

    private BigDecimal quantite ;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    public static LigneVentDto fromEntity(LigneVente ligneVent){
        if (ligneVent == null){
            return null;
        }
        return LigneVentDto.builder()
                .id(ligneVent.getId())
                .quantite(ligneVent.getQuantite())
                .prixUnitaire(ligneVent.getPrixUnitaire())
                .idEntreprise(ligneVent.getIdEntreprise())
                .ventes(VentesDto.fromEntity(ligneVent.getVente()))
                .articleDto(ArticleDto.fromEntity(ligneVent.getArticle()))
                .build();
    }

    public static LigneVente toEntity(LigneVentDto ligneVentDto){
        if (ligneVentDto == null){
            return null;
        }
        LigneVente ligneVent = new LigneVente();
        ligneVent.setId(ligneVentDto.getId());
        ligneVent.setQuantite(ligneVent.getQuantite());
        ligneVent.setPrixUnitaire(ligneVentDto.getPrixUnitaire());
        ligneVent.setIdEntreprise(ligneVentDto.getIdEntreprise());
        ligneVent.setVente(VentesDto.toEntity(ligneVentDto.getVentes()));
        ligneVent.setArticle(ArticleDto.toEntity(ligneVentDto.getArticleDto()));

        return ligneVent;
    }
}
/*


 */