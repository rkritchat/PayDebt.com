package com.gravity.paydebt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity.paydebt.model.UserPwd;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.gravity.paydebt.constants.SecurityConstant.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("Authen");
        try {
            UserPwd userPwd = new ObjectMapper().readValue(request.getInputStream(), UserPwd.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userPwd.getUsername(), userPwd.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET)
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setAudience(authResult.getAuthorities().toArray()[0].toString())
                .setExpiration(Date.from(expirationUTC.toInstant()))
                .compact();
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("---------Fail-------");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
