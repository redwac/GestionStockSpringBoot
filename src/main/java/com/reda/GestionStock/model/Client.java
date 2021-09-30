package com.reda.GestionStock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "client")
public class Client extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Embedded // pour indique que c'est un champ composer, ce champ on peut l'utiliser dans d'autre entitées
    private Adress adress;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    //un client il a une liste de commande client
    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

}
