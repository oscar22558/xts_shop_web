package com.xtsshop.app.filter;

import com.google.common.net.HttpHeaders;
import com.xtsshop.app.service.auth.JWTService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private JWTService jwtService;
    private UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            String accessToken = authHeader.replace("Bearer ", "");

            Map<String, Object> claims = jwtService.parseToken(accessToken);
            String username = (String) claims.get("username");


            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
