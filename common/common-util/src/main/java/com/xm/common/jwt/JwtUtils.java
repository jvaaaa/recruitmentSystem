package com.xm.common.jwt;

import com.sun.deploy.xml.BadTokenException;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * jwt加密和解密的工具类
 */
public class JwtUtils {

    private static final long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    private static final String tokenSignKey = "8677df7fc3a34e26a61c034d5ec8245d";


    // 根据用户 openid 和用户昵称， 签发token
    public static String createToken(String openid, String nickName) {

        return Jwts.builder()
                //分类
                .setSubject("AUTH-USER")

                //设置token有效时长
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))

                //设置主体
                .claim("openid", openid)
                .claim("nickName", nickName)

                //设置签名
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)

                //生成token
                .compact();
    }

    public static String getOpenId(String token){
        if (StringUtils.hasLength(token)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("openid");
        }
        return "";
    }

    public static String getNickName(String token) {
        if (StringUtils.hasLength(token)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("nickName");
        }
        return "";
    }


    /**
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static Claims validateJWT(String token) throws Exception{
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
    }
}
