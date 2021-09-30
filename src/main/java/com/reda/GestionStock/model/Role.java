package com.reda.GestionStock.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "role")
public class Role extends AbstractEntity{

    @Column(name = "rolename")
    private String roleName; // il faut que ca soit comme ca pour que spring le reconnait

    @ManyToOne()
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;

}
