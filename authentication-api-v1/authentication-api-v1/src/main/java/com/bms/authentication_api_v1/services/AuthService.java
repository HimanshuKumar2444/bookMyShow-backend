package com.bms.authentication_api_v1.services;
import com.bms.authentication_api_v1.Models.AppUser;
import com.bms.authentication_api_v1.integrations.DbApi;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    @Autowired
    DbApi dbApi;


    @Value("${auth.secret.key}")
    String secretKey;
     Long expiredTime= 1000000L;
    public String generateToken(String email,String password){
           String crendentials=email+":"+password;
        String jwtToken= Jwts.builder().setSubject(crendentials).
                setExpiration(new Date(System.currentTimeMillis()+expiredTime)).
                setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256,secretKey).
                compact();

        return jwtToken;

    }

    public String decryptToken(String token){
       String crendentials= Jwts.parser().setSigningKey(secretKey).
                parseClaimsJws(token).
                getBody().getSubject();
       return crendentials;
    }

    public boolean verifiedTokens(String token){
// decrypt the token
        String credentials = this.decryptToken(token);
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        AppUser user  = dbApi.getUserById(email);
        if(user == null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

}
