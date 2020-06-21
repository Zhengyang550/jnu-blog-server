package com.jnu.example.core.config.security;


import cn.hutool.core.codec.Base64;
import com.jnu.example.core.constant.enums.ResponseCode;
import com.jnu.example.core.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

/**
 *  @Author: zy
 *  @Date: 2020/4/16 23:33
 *  @Description: JWT工具类
 *  SpringBoot+JWT完成token验证：https://www.jianshu.com/p/9f5b09b3739a
 *  JWT的组成
 *  1、头部（header）  头部一般有两部分信息：类型、加密的算法（通常使用HMAC SHA256）
 *  2、载荷（payload）该部分一般存放一些有效的信息。JWT的标准定义包含五个字段
 *         iss：该JWT的签发者
 *         sub：该JWT所面向的用户
 *         aud：接收该JWT的一方
 *         exp（expires）：什么时候过期，这里是一个Unit的时间戳
 *         iat（issued at）：在什么时候签发的
 *  3、签证（signature）该部分是使用了HS256加密后的数据
 */
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    /*
     * 请求头
     */
    public static final String AUTH_HEADER_KEY = "Authorization";

    /*
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /*
     * 代表这个JWT的接收对象,存入audience
     */
    private static String clientId = "098f6bcd4621d373cade4e832627b4f6";

    /*
     * 密钥, 经过Base64加密, 可自行替换
     */
    private static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

    /*
     * JWT的签发主体，存入issuer
     */
    private static String name = "restapiuser";

    /*
     * 过期时间，时间戳
     */
    private static int expiresSecond = 1728000000;


    /**
     * @Description: 解析jwt
     * @Author: zy
     * @Date: 2020/4/16 23:38
     * @param token : token字符串
     * @Return Claims:
     */
    public static Claims parseJWT(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error(ResponseCode.TOKEN_EXPIRED.getMessage(), e);
            throw new BusinessException(ResponseCode.TOKEN_EXPIRED);
        } catch (Exception e){
            log.error(ResponseCode.TOKEN_INVALID.getMessage(), e);
            throw new BusinessException(ResponseCode.TOKEN_INVALID);
        }
    }

    /**
     * @Description: 构建jwt
     * @Author: zy
     * @Date: 2020/4/16 23:42
     * @param userId: 用户id
     * @param username: 用户名
     * @Return String: token字符串
     */
    public static String generateToken(String userId, String username) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //生成token
            return Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("userId", Base64.encode(userId))
                    .setSubject(username)
                    .setIssuer(clientId)
                    .setIssuedAt(new Date())
                    .setAudience(name)
                    .setExpiration(new Date(System.currentTimeMillis() + expiresSecond))
                    .signWith(signatureAlgorithm, signingKey)
                    .compact();
        } catch (Exception e) {
            log.error(ResponseCode.SIGNATURE_ERROR.getMessage(), e);
            throw new BusinessException(ResponseCode.SIGNATURE_ERROR);
        }
    }

    /**
     * 从token中获取用户名
     * @param token：token字符串
     * @return String: 用户名
     */
    public static String getUsernameFromToken(String token){
        return parseJWT(token).getSubject();
    }

    /**
     * 从token中获取用户ID
     * @param token: token字符串
     * @return String: 用户id
     */
    public static String getUserIdFromToken(String token){
        String userId = parseJWT(token).get("userId", String.class);
        return new String(Base64.decode(userId));
    }

    /**
     * 获取过期时间
     * @param token: token字符串
     * @return Date
     */
    public static Date getExpirationDateFromToken(String token) {
        return parseJWT(token).getExpiration();
    }

    /**
     * 是否已过期
     * @param token: token字符串
     * @return Boolean
     */
    public static Boolean isTokenExpired(String token) {
        return parseJWT(token).getExpiration().before(new Date());
    }

    /**
     * 校验token是否有效
     * @param token: token字符串
     * @param username: 用户名
     * @return Boolean
     */
    public static Boolean validateToken(String token,String username) {
        if(username == null){
            username ="";
        }
        return (username.equals(getUsernameFromToken(token))
                && !isTokenExpired(token)
        );
    }
}

