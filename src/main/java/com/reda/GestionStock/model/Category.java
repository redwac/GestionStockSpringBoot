package com.reda.GestionStock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "category")
public class Category extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    // parce que une categorie conient luesieur article on ajoute une liste d'article aussi le mapping qui est l'attribue category dans la class Article
    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    @Column(name = "identreprise")
    private Integer idEntreprise;


}
