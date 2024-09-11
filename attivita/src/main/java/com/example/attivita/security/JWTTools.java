package com.example.attivita.security;

import com.example.attivita.exceptions.UnauthorizedException;
import com.example.attivita.tokens.Tokens;
import com.example.attivita.tokens.TokensRepository;
import com.example.attivita.user.User;
import com.example.attivita.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String secret;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokensRepository tokensRepository;

    public Tokens createTokens(User user){
        String accessToken = Jwts.builder().setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*45))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

        String refreshToken = Jwts.builder().setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60 *24 *7))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

        if(user.getTokens()!=null) {
            Tokens tokens = user.getTokens();
            tokens.setAccessToken(accessToken);
            tokens.setRefreshToken(refreshToken);
            user.setTokens(tokens);
            tokensRepository.save(tokens);
            return tokens;
        }else{
            Tokens tokens = new Tokens();
            tokens.setAccessToken(accessToken);
            tokens.setRefreshToken(refreshToken);
            tokens.setUser(user);
            user.setTokens(tokens);
            tokensRepository.save(tokens);
            return tokens;
        }
    }
    public User verifyToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token).getBody();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String userId = claims.getSubject();
            return userRepository.findById(Long.valueOf(userId)).get();
        }catch (Exception e){
            throw new UnauthorizedException("Il token non è valido! Per favore effettua nuovamente il login o refresha la pagina!");
        }
    }
    public Tokens verifyRefreshToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token).getBody();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String userId= claims.getSubject();
            return  this.createTokens(userRepository.findById(Long.valueOf(userId)).get());
        }catch (Exception e){
            throw new UnauthorizedException("Il refresh token non è valido. Accedi nuovamente.");
        }
    }

    public String extractIdFromToken(String token){
        return  Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseClaimsJws(token).getBody().getSubject();
    }
}