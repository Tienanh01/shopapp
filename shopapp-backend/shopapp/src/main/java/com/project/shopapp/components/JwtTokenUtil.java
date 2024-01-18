package com.project.shopapp.components;

import com.project.shopapp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secrectKey}")
    private String secrectKey ;


    // sinh ra token , co screct key ,
    public String gennerateToken (User user){
        Map<String, Objects> claims = new HashMap<>();
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration *1000L))
                    .signWith(setSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return  token;
        }catch (Exception e){
            System.out.printf(" Can not create jwt token , error "+e.getMessage());
            return null;
        }
    }

    private Key setSignInKey(){
        byte[] bytes = Decoders.BASE64.decode(secrectKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token ){
        return  Jwts.parser()
                .setSigningKey(setSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  <T> T extractClaim (String token , Function<Claims , T> claimsResolve ){
        final Claims claims = this.extractAllClaims(token);
       return  claimsResolve.apply(claims);

    }

        public boolean isTokenExpired(String token ){
        // ngafy qua han
        Date expirationDate = this.extractClaim(token,Claims::getExpiration);

        // return ngay quan han co truoc ngay hien tai khong
        return  expirationDate.before(new Date()) ;
        }
}
