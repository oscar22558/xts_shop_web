package com.xtsshop.app.service.auth;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Privilege;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.form.user.SpringUser;
import com.xtsshop.app.service.users.UsersCRUDService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SpringUserService implements UserDetailsService {

    private UsersCRUDService usersCRUDService;
    public SpringUserService(UsersCRUDService usersCRUDService){
        this.usersCRUDService = usersCRUDService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = usersCRUDService.findUserByUserName(username);
        if(appUser == null) throw new UsernameNotFoundException("No such user " + username);
        return new SpringUser(appUser, toGrantedAuthorities(toPrivileges(appUser)));
    }

    public SpringUser loadUserById(Long id) throws RecordNotFoundException {
        AppUser appUser = usersCRUDService.findUserById(id);
        return new SpringUser(appUser, toGrantedAuthorities(toPrivileges(appUser)));
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
