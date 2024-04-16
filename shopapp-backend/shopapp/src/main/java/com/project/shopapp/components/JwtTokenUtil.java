package com.project.shopapp.components;

import com.project.shopapp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secrectKey}")
    private String secrectKey;


    // sinh ra token , co screct key ,
    public String gennerateToken (User user){
        Map<String, Objects> claims = new HashMap<>();
    public String gennerateToken(User user) {
        Map<String, String> claims = new HashMap<>();

//        this.generateSecrectKey();
        claims.put("phoneNumber", user.getPhoneNumber());

        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(setSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        } catch (Exception e) {
            throw new InvalidParameterException("Can not create jwt token " + e.getMessage());
//            System.out.printf(" Can not create jwt token , error "+e.getMessage());
//            return null;
        }
    }

    private Key getSignInKey(){
    private String generateSecrectKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        String secrectKey = Encoders.BASE64.encode(keyBytes);
        return secrectKey;
    }

    private Key setSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secrectKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(setSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolve) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolve.apply(claims);

    }

    public boolean isTokenExpired(String token) {
        // ngafy qua han
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);

        // return ngay quan han co truoc ngay hien tai khong
        return expirationDate.before(new Date());
    }


    public String extractPhoneNumber(String token ){
        return extractClaim(token,Claims::getSubject);
    }


    public boolean validateToken(String token , UserDetails userDetails){
        String phoneNumber  = extractPhoneNumber(token) ;
        return (phoneNumber.equals(userDetails.getUsername())  && isTokenExpired(token)) ;
    }

}
