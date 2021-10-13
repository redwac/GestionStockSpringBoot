package com.reda.GestionStock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reda.GestionStock.model.Role;
import com.reda.GestionStock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class RoleDto {

    private Integer id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDto utilisateur;

    public RoleDto fromEntity(Role role){
        if (role == null){
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .utilisateur(UtilisateurDto.fromEntity(role.getUtilisateur()))
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if (roleDto == null){
            return null;
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setUtilisateur(UtilisateurDto.toEntity(roleDto.getUtilisateur()));

        return role;
    }
}
