package com.reda.GestionStock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) //????????
@Entity
@Table(name= "article")
public class Article extends AbstractEntity{

    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixuniteht")
    private BigDecimal prixUniteHt;

    @Column(name = "tauxTva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitettc")
    private BigDecimal prixUniteTtc;

    @Column(name = "photo")
    private String photo;

    //chaque article appartien a une categorie on cree l'attribue categorie pour lie table article et categorie aussi le mapping
    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @Column(name = "identreprise")
    private Integer idEntreprise;
}
