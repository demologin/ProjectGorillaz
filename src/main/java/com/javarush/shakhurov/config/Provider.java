package com.javarush.shakhurov.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.javarush.shakhurov.model.User;
import javalinjwt.JWTGenerator;
import javalinjwt.JWTProvider;

public class Provider {

    public static JWTProvider<User> create() {
        JWTGenerator<User> generator = (user, alg) -> {
            JWTCreator.Builder token = JWT.create()
                    .withClaim("name", user.getName())
                    .withClaim("role", user.getRole());
            return token.sign(alg);
        };

        Algorithm algorithm = Algorithm.HMAC256("very_secret");
        JWTVerifier verifier = JWT.require(algorithm).build();

        return new JWTProvider<>(algorithm, generator, verifier);
    }
}
