package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.Article;
import com.reda.GestionStock.model.MvStk;
import com.reda.GestionStock.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
public class MvStkDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvtStock typeMvtStock;

    private ArticleDto article;

    private Integer idEntreprise;

    public MvStkDto fromEntity(MvStk mvStk){
        if (mvStk == null){
            return null;
        }
        return MvStkDto.builder()
                .id(mvStk.getId())
                .dateMvt(mvStk.getDateMvt())
                .quantite(mvStk.getQuantite())
                .idEntreprise(mvStk.getIdEntreprise())
                .typeMvtStock(mvStk.getTypeMvtStock())
                .article(ArticleDto.fromEntity(mvStk.getArticle()))
                .build();
    }
    public MvStk toEntity(MvStkDto mvStkDto){
        if (mvStkDto == null){
            return null;
        }
        MvStk mvStk = new MvStk();
        mvStk.setId(mvStkDto.getId());
        mvStk.setDateMvt(mvStkDto.getDateMvt());
        mvStk.setQuantite(mvStkDto.getQuantite());
        mvStk.setIdEntreprise(mvStkDto.getIdEntreprise());
        mvStk.setTypeMvtStock(mvStkDto.getTypeMvtStock());
        mvStk.setArticle(ArticleDto.toEntity(mvStkDto.getArticle()));

        return mvStk;
    }
}
