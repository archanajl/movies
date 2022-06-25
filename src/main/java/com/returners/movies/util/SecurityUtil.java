package com.returners.movies.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class SecurityUtil {

    public static String[] getRolesfromHeader(String authorizartionHeader){

        String token = authorizartionHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
        return roles;

    }

}
