package com.reda.GestionStock.dto;

import com.reda.GestionStock.model.CommandeFournissuer;
import com.reda.GestionStock.model.Fournisseur;
import com.reda.GestionStock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CommandeFournissuerDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private Integer idEntreprise;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournissuerDto fromEntity(CommandeFournissuer commandeFournissuer){
        if (commandeFournissuer == null){
            return null;
        }
        return CommandeFournissuerDto.builder()
                .id(commandeFournissuer.getId())
                .code(commandeFournissuer.getCode())
                .dateCommande(commandeFournissuer.getDateCommande())
                .idEntreprise(commandeFournissuer.getIdEntreprise())
                .fournisseur(FournisseurDto.fromEntity(commandeFournissuer.getFournisseur()))
                .build();
    }
    public static CommandeFournissuer toEntity(CommandeFournissuerDto commandeFournissuerDto){
        if (commandeFournissuerDto == null){
            return null;
        }
        CommandeFournissuer commandeFournissuer = new CommandeFournissuer();
        commandeFournissuer.setId(commandeFournissuerDto.getId());
        commandeFournissuer.setCode(commandeFournissuerDto.getCode());
        commandeFournissuer.setDateCommande(commandeFournissuer.getDateCommande());
        commandeFournissuer.setIdEntreprise(commandeFournissuerDto.getIdEntreprise());
        commandeFournissuer.setFournisseur(FournisseurDto.toEntity(commandeFournissuerDto.getFournisseur()));

        return commandeFournissuer;
    }
}
