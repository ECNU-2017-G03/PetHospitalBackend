package com.ecnu.g03.pethospital.util;

import com.ecnu.g03.pethospital.model.entity.Audience;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 0:51
 */
@Component
public class JwtUtil {
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private static Audience audience;
    private static Key key;

    public static String createJWT(UserEntity user) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Date exp = new Date(nowMillis + audience.getExpiresSecond() * 1000L);

            JwtBuilder builder = Jwts.builder()
                    .setSubject(user.getId())              // 所有人
                    .setIssuer(audience.getClientId())    // 签发主体；
                    .setIssuedAt(new Date())              // 签发时间；
                    .setAudience(audience.getName())      // 接收对象；
                    .claim("name", user.getName())
                    .signWith(key)
                    .setExpiration(exp)
                    .setNotBefore(now);

            return builder.compact();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Jws<Claims> parseJWT(String jsonWebToken) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jsonWebToken);

    }

    public static String getUserId(String token) {
        return parseJWT(token).getBody().getSubject();
    }

    public static String getUserName(String token) {
        return parseJWT(token).getBody().get("name", String.class);
    }

    @Autowired
    public void initKeys(Audience audience) {
        JwtUtil.audience = audience;

        byte[] apiKeySecretBytes = audience.getBase64Secret().getBytes();
        key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
}
