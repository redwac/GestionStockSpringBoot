package com.reda.GestionStock.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ExtentedUser extends User {

    @Setter
    @Getter
    private Integer idEntreprise;

    public ExtentedUser(String username, String password,
                        Integer id, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ExtentedUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
