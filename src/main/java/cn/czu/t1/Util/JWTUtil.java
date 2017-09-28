package cn.czu.t1.Util;

import cn.czu.t1.controler.Constant;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JWTUtil {

    public static SecretKey generalKey(){
        String stringKey = Constant.JWT_SECRET;
        byte[] encodedKey = Base64.getEncoder().encode(stringKey.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static String createJWT(String id, String subject, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512 ;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
//        Key key = MacProvider.generateKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, generalKey());
        if (ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static int parseJWT(String jwt) {
        try{
            Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();
            // we can trust the jwt
            return 1;
        }catch (SignatureException e){
             // we can NOT trust the jwt
            return 0;
        }catch (MissingClaimException e) {
            return 2;
            // we get here if the required claim is not present

        } catch (IncorrectClaimException e) {
            return 3;
            // we get here if the required claim has the wrong value

        }catch (ExpiredJwtException e){
            // time out
            return 4;
        }catch (MalformedJwtException e){

            return 5;
        }catch (Exception e){

            return -1;
        }
    }

    public static String getIdFromJWT(String jwt){
        if(parseJWT(jwt) != 1){
            return null;
        }
        Claims claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();

        return claims.getId();
//        String subject = claims.getSubject();
//        String issuer = claims.getIssuer();
//        String expiration = String.valueOf(claims.getExpiration());
    }
}
