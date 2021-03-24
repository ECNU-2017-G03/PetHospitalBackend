package com.ecnu.g03.pethospital.util;

import com.ecnu.g03.pethospital.model.entity.Audience;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 0:51
 */
public class JwtUtil {
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static Key key;

    public static String createJWT(String name, Audience audience) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = audience.getBase64Secret().getBytes();
            Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Date exp = new Date(nowMillis + audience.getExpiresSecond());

            JwtBuilder builder = Jwts.builder()
                    .setSubject(name)               // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(key)
                    .setExpiration(exp)
                    .setNotBefore(now);

            return builder.compact();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(base64Security.getBytes())
                .build()
                .parseClaimsJws(jsonWebToken);
    }

    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }

    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }
}
