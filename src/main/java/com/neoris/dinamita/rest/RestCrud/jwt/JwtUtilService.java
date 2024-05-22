package com.neoris.dinamita.rest.RestCrud.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtUtilService {

    //Clave secreta utilizada para firmar y verificar el token
    private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";
    //Duracion temporal del token en 15 minutos
    private static final long JWT_TIME_VALIDITY = 1000 * 60  * 15;


    public String generateToken(UserDetails userDetails) {
        var claims = new HashMap<String, Object>();
        return Jwts.builder() //Construcción del Token JWT
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) //Establece al username como el subject
                .setIssuedAt(new Date(System.currentTimeMillis())) //Fecha de emision de token
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY)) //Fecha de expiracion
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY) //Firma el token utilizando HS256 y la clave secreta
                .compact(); //Compacta el token
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        //Obtiene el nombre de usuario almancenado en el token y lo compara con el nombre de usuario de (UserDetails)
        return extractClaim(token, Claims::getSubject).equals(userDetails.getUsername())
                && !extractClaim(token, Claims::getExpiration).before(new Date()); //Verifica la fecha de expiracion
    }

    //Extraer informacion del token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    //Extraer el nombre de usuario del Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

}
