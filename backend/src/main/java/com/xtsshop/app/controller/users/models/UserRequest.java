package com.xtsshop.app.controller.users.models;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.helpers.DateTimeHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserRequest{
    @Nullable
    private String username;
    @Nullable
    private String password;
    @Nullable
    private String email;
    @Nullable
    private String phone;
    private BCryptPasswordEncoder encoder;
    private List<Role> roles;
    public UserRequest(String username, String password, String email, @Nullable String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
    public UserRequest setEncoder(BCryptPasswordEncoder encoder){
        this.encoder = encoder;
        return this;
    }
    public UserRequest setRoles(List<Role> roles){
        this.roles = roles;
        return this;
    }
    public AppUser toEntity(){
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        Date now = dateTimeHelper.now();
        return new AppUser(now, now, username, encoder.encode(password), email, phone, roles);
    }
    public AppUser update(AppUser originalUser){
        originalUser.setUsername(username != null ? username : originalUser.getUsername());
        originalUser.setPassword(password != null ? encoder.encode(password) : originalUser.getPassword());
        originalUser.setEmail(email != null ? email : originalUser.getEmail());
        originalUser.setPhone(phone != null ? phone : originalUser.getPhone());
        return originalUser;
    }
}
