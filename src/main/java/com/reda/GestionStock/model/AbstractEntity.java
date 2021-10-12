package com.reda.GestionStock.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
//permet ed creer un builder on utilsant le designe patern builder qui permet de construir un objet en exposant des methode qui contient le meme nom de l'attribue avec une methode qui vas construir un objet avec les meme champ des tables
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)// fourni par spring il consulte la classe et il vas faire la MAJ creationDate et lastModifiedDate sur la base données
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate // on presiser pour Hibernate que c'est une date de creation
    @Column(name = "creationDate", nullable = false)
    private Instant creationDate; // puisque on utilise java 11 on peut utiliser Instant a la place de Date

    @LastModifiedDate
    @Column(name = "lastModifiedDate", nullable = false)
    private Instant lastModifiedDate;
}
