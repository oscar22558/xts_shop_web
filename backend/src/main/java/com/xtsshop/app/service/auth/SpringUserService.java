package com.xtsshop.app.service.auth;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Privilege;
import com.xtsshop.app.db.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SpringUserService implements UserDetailsService {

    private UsersService usersService;
    public SpringUserService(UsersService usersService){
        this.usersService = usersService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = usersService.getUserByUserName(username);
        if(appUser == null) throw new UsernameNotFoundException("No such user " + username);
        List<GrantedAuthority> authorities = toGrantedAuthorities(toPrivileges(appUser));
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    private List<String> toPrivileges(AppUser user){
        Stream<Role> rolesStream = user.getRoles()
                .stream();
        Stream<Privilege> privilegesStream = rolesStream.flatMap(role -> role.getPrivileges().stream());
        Stream<String> privilegeName = privilegesStream.map(privilege -> privilege.getName().name());
        List<String> privileges = privilegeName.collect(Collectors.toList());
        List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
        roles.addAll(privileges);
        return roles;
    }
    private List<GrantedAuthority> toGrantedAuthorities(List<String> privileges){
        return privileges.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
