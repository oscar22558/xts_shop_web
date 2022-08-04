package com.xtsshop.app.features.users.models;

import com.xtsshop.app.db.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SpringUser implements UserDetails {
    private AppUser appUser;
    private List<GrantedAuthority> grantedAuthorities;
    public SpringUser(AppUser appUser, List<GrantedAuthority> grantedAuthorities) {
        this.appUser = appUser;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
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

    public String getEmail(){
        return appUser.getEmail();
    }
    public String getPhone(){
        return appUser.getPhone();
    }
    public Long getId(){
        return appUser.getId();
    }
    public AppUser getUser(){ return appUser; }

}
