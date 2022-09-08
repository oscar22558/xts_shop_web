package com.xtsshop.app.features.authentication;

import com.google.common.net.HttpHeaders;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.advices.exception.UnAuthorizationException;
import com.xtsshop.app.features.users.models.SpringUser;
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
        authorizeUser(request);
        filterChain.doFilter(request, response);
    }

    private void authorizeUser(HttpServletRequest request){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null) return;
        String accessToken = authHeader.replace("Bearer ", "");
        if(accessToken.isBlank()) throw new UnAuthorizationException();

        Map<String, Object> claims = jwtService.parseToken(accessToken);
        Integer id = (Integer) claims.get("userId");
        try {
            SpringUser userDetails = springUserService.loadUserById(Integer.toUnsignedLong(id));
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
