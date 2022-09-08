package com.xtsshop.app;

import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.features.authentication.JWTAuthenticationFilter;
import com.xtsshop.app.features.authentication.SpringUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpringUserService springUserService;
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(SpringUserService springUserService) {
        this.springUserService = springUserService;
    }

    @Autowired
    public void setJwtAuthenticationFilter(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/api/categories/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority(RoleType.ROLE_ADMIN.name())

                .antMatchers(HttpMethod.POST, "/api/items/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/api/items/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/items/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/items/**").hasAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/auth/parse").hasAuthority(RoleType.ROLE_ADMIN.name())

                .antMatchers(HttpMethod.PATCH, "/api/users/password").hasAnyAuthority(RoleType.ROLE_USER.name())
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority(RoleType.ROLE_USER.name())
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()

                .antMatchers("/api/payment-intent").hasAnyAuthority(RoleType.ROLE_USER.name())
                .antMatchers(HttpMethod.POST, "/webhook").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/invoice").permitAll()

                .antMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/items/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/brands/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/auth/user").hasAnyAuthority(RoleType.ROLE_USER.name())
                .antMatchers(HttpMethod.POST, "/api/auth/valid").hasAnyAuthority(RoleType.ROLE_USER.name())
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/api/orders").hasAnyAuthority(RoleType.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.GET, "/storage/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
