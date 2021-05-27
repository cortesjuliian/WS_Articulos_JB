/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author julia
 */
public class JwtTokenHelper {

    private final Long currentTimeInMillis = System.currentTimeMillis();

    public JwtTokenHelper() {
    }

    public String generateJwtTokenSucces(HashMap<String, Object> valToken) {
        return Jwts.builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .setId(UUID.randomUUID().toString())
                .claim("valToken", valToken)
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
    }

    public String generateJwtTokenError(HashMap<String, Object> valToken) {
        return Jwts
                .builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .claim("valToken", valToken)
                .setId(UUID.randomUUID().toString())
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
    }

    public String issueTokenAuth() {
        //Se crea token                
        String jwtToken = Jwts.builder()
                .setIssuer("APIActividad1")
                .setSubject("WS_CONSUMES_API")
                .setAudience("APP")
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .setId(UUID.randomUUID().toString())
                .signWith(RestSecurityFilter.KEY, SignatureAlgorithm.HS512) //Algoritmo de encriptación
                .compact();
        return jwtToken;
    }

    private Date getExpirationDate() {
        String sTimeOutSession = "960";
        Long lTimeOutSession = new Long(sTimeOutSession);
        lTimeOutSession = lTimeOutSession * 1000;
        return new Date(currentTimeInMillis + lTimeOutSession);
    }
}
