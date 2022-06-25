package com.returners.movies.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.returners.movies.model.AuthUserInfo;

public class SecurityUtil {

    private final static String ALGO_CODE = "secret";

    public static AuthUserInfo getAuthHeaderDetails(String authorizartionHeader){

        AuthUserInfo authUserInfo = new AuthUserInfo();
        String token = authorizartionHeader.substring("Bearer ".length());
        Algorithm algorithm = getAlgorithmDetails();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        authUserInfo.setToken(token);
        authUserInfo.setUserName(decodedJWT.getSubject());
        authUserInfo.setRoles(decodedJWT.getClaim("roles").asArray(String.class));
        return authUserInfo;

    }

    public static Algorithm getAlgorithmDetails(){
        return Algorithm.HMAC256(ALGO_CODE.getBytes());
    }

}
