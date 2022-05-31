package com.xtsshop.app.filter;

import com.google.common.net.HttpHeaders;
import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.form.user.SpringUser;
import com.xtsshop.app.domain.service.auth.JWTService;
import com.xtsshop.app.domain.service.auth.SpringUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@Transactional
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private SpringUserService springUserService;

    public JWTAuthenticationFilter(JWTService jwtService, SpringUserService springUserService) {
        this.jwtService = jwtService;
        this.springUserService = springUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null) {
            String accessToken = authHeader.replace("Bearer ", "");

            Map<String, Object> claims = jwtService.parseToken(accessToken);
            Integer id = (Integer) claims.get("userId");
            try {
                SpringUser userDetails = springUserService.loadUserById(Integer.toUnsignedLong(id));
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
