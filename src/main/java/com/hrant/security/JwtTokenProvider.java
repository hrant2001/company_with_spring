package com.hrant.security;

import com.hrant.model.Role;
import io.jsonwebtoken.*;
import com.hrant.exception.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${jwt.expiration}")
    private long expiration;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String generateJwtToken(String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration + 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("JWT token expired or invalid {}", e.getMessage());
            throw new JwtAuthenticationException("JWT token expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserNameFromJwtToken(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
