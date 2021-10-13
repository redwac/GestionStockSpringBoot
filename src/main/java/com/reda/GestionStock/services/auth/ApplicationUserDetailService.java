package com.reda.GestionStock.services.auth;

import com.reda.GestionStock.dto.UtilisateurDto;
import com.reda.GestionStock.exception.EntityNotFoundException;
import com.reda.GestionStock.exception.ErrorCodes;
import com.reda.GestionStock.model.Utilisateur;
import com.reda.GestionStock.model.auth.ExtentedUser;
import com.reda.GestionStock.repository.UtilisateurRepository;
import com.reda.GestionStock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

// cette classe est utilisé pour la sécurité
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UtilisateurDto utilisateur = utilisateurService.findUtilisateurByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(roleDto -> authorities.add(new SimpleGrantedAuthority(roleDto.getRoleName())));

        return new ExtentedUser(utilisateur.getEmail(), utilisateur.getMotDePass(), utilisateur.getEntreprise().getId(), authorities);
    }
}
