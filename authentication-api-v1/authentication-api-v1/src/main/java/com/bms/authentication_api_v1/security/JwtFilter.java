package com.bms.authentication_api_v1.security;

import com.bms.authentication_api_v1.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
@Component
// /verify-token -> protected
// /generate-token -> not protected
public class JwtFilter extends OncePerRequestFilter  {
    @Autowired
    AuthService authService;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            // We got the token now we need to validate that this token is a genuine token or not.
            boolean isValid = authService.verifiedTokens(token);
            if (isValid == false) {
                // I am not going to set any kind of authentication and i will return from here it self
                // before filtering if i am not setting any kind of authetication that
                // means i am rejecting the reuquest
                filterChain.doFilter(request, response);
                return;
            }

            String crednetials = authService.decryptToken(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(crednetials, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);

    }


}