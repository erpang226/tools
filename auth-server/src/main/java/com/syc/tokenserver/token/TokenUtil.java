package com.syc.tokenserver.token;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * Token工具类
 *
 * @author syc
 * @version [版本号, 2018/1/24]
 * @see [可以参考 ]
 */
public class TokenUtil {

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.getEncoder().encode(stringKey.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param subject
     * @return
     * @throws Exception
     */
    public static String createJWT(String subject) {
        String id = UUID.randomUUID().toString();
        long ttlMillis = Constant.BIZ_JWT_TTL;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setHeaderParam("typ", "jwt")
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    /**
     * 创建jwt
     *
     * @param subject
     * @return
     * @throws Exception
     */
    public static String createRefreshJWT(String subject) {
        String id = UUID.randomUUID().toString();
        long ttlMillis = Constant.REFRESH_JWT_TTL;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setHeaderParam("typ", "jwt")
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }


    public static void main(String[] args) {


        List<String> roles = new ArrayList<>();
        roles.add("zhzg");
        PayLoad payLoad = new PayLoad("2", "C", 1, roles);


        try {
            String id = UUID.randomUUID().toString();
            System.out.println(id);
            String jwt = createJWT(JSONObject.toJSONString(payLoad));
            System.out.println(jwt);
            System.out.println("parsing jwt: " + jwt);

            Claims claims = parseJWT(jwt);


            System.out.println("expiry time :" + claims.getExpiration());
            System.out.println("iat: " + claims.getIssuedAt());
            System.out.println("Payload :" + claims.getSubject());


        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkZDc3MDI1OS0zZTk0LTQxNGEtODE4My1kYjMyOTYzZGZmNGQiLCJpYXQiOjE1MTc1NTEyMjMsInN1YiI6Intcblx0XCJjb3VudFwiOjEwLjAsXG5cdFwiY291bnRMb25nXCI6MTAwLjAsXG5cdFwib3BlbklkXCI6XCJvcGVuSWQxMjNcIixcblx0XCJ0aW1lXCI6MTUxNzU1MTIyMjY4Myxcblx0XCJ1c2VySWRcIjoxXG59IiwiZXhwIjoxNTE3NTU0ODIzfQ.-efWQ5vyHb3Auyd6xuxD7Sm51RIizPhxbIR7LAI4OF4
         *
         * eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9
         * .
         * eyJqdGkiOiJkZDc3MDI1OS0zZTk0LTQxNGEtODE4My1kYjMyOTYzZGZmNGQiLCJpYXQiOjE1MTc1NTEyMjMsInN1YiI6Intcblx0XCJjb3VudFwiOjEwLjAsXG5cdFwiY291bnRMb25nXCI6MTAwLjAsXG5cdFwib3BlbklkXCI6XCJvcGVuSWQxMjNcIixcblx0XCJ0aW1lXCI6MTUxNzU1MTIyMjY4Myxcblx0XCJ1c2VySWRcIjoxXG59IiwiZXhwIjoxNTE3NTU0ODIzfQ
         * .
         * -efWQ5vyHb3Auyd6xuxD7Sm51RIizPhxbIR7LAI4OF4
         */
        System.out.println("header: " + new String(Base64.getDecoder().decode("eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9")));
        System.out.println("sub: " + new String(Base64.getDecoder().decode("eyJqdGkiOiIzZmQxYjE0NS02M2Q3LTRkMDEtOTc0Zi00NDFlNmI4ZjkzOGMiLCJpYXQiOjE1MTc1MzYyNzMsInN1YiI6Intcblx0XCJjb3VudFwiOjEyMy4wLFxuXHRcImNvdW50TG9uZ1wiOjEyMzQ1Ni4wLFxuXHRcIm9wZW5JZFwiOlwib3BlbklkMTIzXCIsXG5cdFwidGltZVwiOjE1MTc1MzYyNzA0ODEsXG5cdFwidXNlcklkXCI6MVxufSIsImV4cCI6MTUxNzUzOTg3M30")));


    }


}
