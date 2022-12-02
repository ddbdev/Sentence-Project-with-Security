package it.cgmconsulting.msuser.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import it.cgmconsulting.msuser.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class JwtTokenProvider {

    public static String generateToken(Users user, String role) {

        Map<String, Object> payloadClaims = new HashMap<String, Object>();
        payloadClaims.put("roles", role);
        payloadClaims.put("isEnabled", user.isEnabled());
        payloadClaims.put("id", user.getId());
        payloadClaims.put("username", user.getUsername());

        JWTCreator.Builder builder = JWT.create()
                .withSubject(user.getUsername()) 			// sub: username
                .withIssuer(SetValuesFromApplicationYaml.ISSUER);	// iss: application name
        final Instant now = Instant.now();
        builder
                .withIssuedAt(Date.from(now)) // iat: jwt creation date
                .withExpiresAt(Date.from(now.plus(SetValuesFromApplicationYaml.JWT_EXPIRATION_IN_SECONDS, ChronoUnit.SECONDS))); // exp: jwt expiration date

        if (payloadClaims.isEmpty()) {
            log.warn("You are building a JWT without header claims");
        }
        for (Map.Entry<String, Object> entry : payloadClaims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        return "Bearer " + builder.sign(Algorithm.HMAC512(SetValuesFromApplicationYaml.JWT_SECRET));
    }
}
