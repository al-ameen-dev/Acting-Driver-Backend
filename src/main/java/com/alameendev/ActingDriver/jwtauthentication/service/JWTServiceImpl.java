package com.alameendev.ActingDriver.jwtauthentication.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService{
        @Autowired
        private final MessageDigest messageDigest;
        private static final String SECRET_KEY = "mysecret";
        public String extractUserName(String jwtToken) {
            return extractClaim(jwtToken, Claims::getSubject);
        }
        public String generateTokenForUser(UserDetails userDetails)
        {
            return generateToken(new HashMap<>(),userDetails);
        }

        public boolean isTokenValid(String jwtToken, UserDetails userDetails)
        {
            String userName = extractUserName(jwtToken);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
        }

        private boolean isTokenExpired(String jwtToken) {
            return extractExpiration(jwtToken).before(new Date());
        }

        private Date extractExpiration(String jwtToken) {
            return extractClaim(jwtToken,Claims::getExpiration);
        }


        public <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver)
        {
            final Claims claims = extractAllClaims(jwtToken);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String jwtToken)
        {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        }

        private String generateToken(
                Map<String, Object> extraClaims,
                UserDetails userDetails
        )
        {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+(1000 * 60 * 24)))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        private Key getSigningKey() {
            byte[] keyBytes = messageDigest.digest(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            BigInteger toNumber = new BigInteger(1, keyBytes);
            StringBuilder hexStr = new StringBuilder(toNumber.toString(16));
            while (hexStr.length() < 32) {
                hexStr.insert(0, '0');
            }
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(hexStr.toString()));
        }

}
