package com.eazybytes.utils;

import com.eazybytes.constants.SecurityConstants;
import com.eazybytes.model.UserDetailImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtils {

    //@Value("${ihab.jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        System.out.println("userdetails " + userDetail);
        return Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date().getTime() + 3600000))).signWith(Keys.hmacShaKeyFor(
                SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8))).compact();

    }
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(
                    SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8))).build().parse(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public String getUsernameFromJwt(String token){
        return String.valueOf(Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody().getSubject());
    }

}
