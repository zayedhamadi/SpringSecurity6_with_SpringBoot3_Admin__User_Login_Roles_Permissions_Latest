package com.Spring_Security_updated.Spring_Security_updated.Configuration;


import com.Spring_Security_updated.Spring_Security_updated.Entity.OurUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class OurUserInfoDetails implements UserDetails {


    private final String email;
    private final String password;
    private final List<GrantedAuthority> roles;

    public OurUserInfoDetails(OurUser u) {
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.roles = Arrays.stream(u.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
