package com.exmaple.project.JWT.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final int TOKEN_VALIDITY = 3600*5;
    SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode("JWTlearningisjhfihshidfbdsjajfbsdkjbasjdbgiuadgsnjnadsf"));

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY)
                .build().parseUnsecuredClaims(token).getPayload();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String userName = getUserNameFromToken(jwtToken);
        return (userName.equals(userDetails.getUsername()) && isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +TOKEN_VALIDITY *1000 ))
                .signWith(SECRET_KEY)
                .compact();
    }

}
