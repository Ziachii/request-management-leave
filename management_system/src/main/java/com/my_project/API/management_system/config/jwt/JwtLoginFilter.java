package com.my_project.API.management_system.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            return authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                response.getWriter().write("Email or password is incorrect");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                response.getWriter().flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Authentication Failed: " + e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                response.getWriter().flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String secretKey = "abcddfdsf1243abcddfdsf1243abcddfdsf1243";

        // Generate the JWT token
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setIssuedAt(new Date())
                .claim("authorities", authResult.getAuthorities())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
                .setIssuer("online-request-management.com")
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();


        response.setHeader("Authorization", "Bearer " + token);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Login successful\", \"token\": \"" + token + "\"}");
        response.getWriter().flush();
    }
}
