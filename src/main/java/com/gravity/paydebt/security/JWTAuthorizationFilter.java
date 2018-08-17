package com.gravity.paydebt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gravity.paydebt.constants.SecurityConstant.HEADER_STRING;
import static com.gravity.paydebt.constants.SecurityConstant.SECRET;
import static com.gravity.paydebt.constants.SecurityConstant.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            System.out.println("Header is null :: " + header);
        } else {
            UsernamePasswordAuthenticationToken token = getTokenFromHeader(header);
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        chain.doFilter(request,response);
    }

    public UsernamePasswordAuthenticationToken getTokenFromHeader(String header){
        Claims body = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(header.replace(TOKEN_PREFIX, ""))
                .getBody();
        String username = body.getSubject();
        String role = body.getAudience();
        return username == null ? null : new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(role));
    }


}
