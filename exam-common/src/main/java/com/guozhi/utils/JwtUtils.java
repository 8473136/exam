package com.guozhi.utils;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.common.JwtPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiuchangLan
 * @date 2020/7/16 15:04
 */
@Slf4j
public class JwtUtils {

    public static final String TOKEN_SECRET = "A5D833ABE7685BA6AB2E30BD8156E6E";

    /**
     * @description 创建token
     * @author LiuChangLan
     * @since 2020/7/16 15:19
     * @param jwtPayload jwt载体
     * @param expireTimeInSecond 过期时间 秒
     */
    public static String createToken(JwtPayload jwtPayload, Long expireTimeInSecond){
        Algorithm alg = Algorithm.HMAC256(TOKEN_SECRET);
        Date currentTime = new Date();
        String token = JWT.create()
                // 签发时间
                .withIssuedAt(currentTime)
                // 过期时间
                .withExpiresAt(new Date(currentTime.getTime() + expireTimeInSecond * 1000))
                // 分配JWT的ID
                .withJWTId(UUIDUtils.ramdomUUID())
                // 定义公共域信息
                .withClaim("loginUser", JSON.toJSONString(jwtPayload))
                // 加密的密钥
                .sign(alg);
        return token;
    }

    /**
     * @description 验证token
     * @author LiuChangLan
     * @since 2020/6/29 15:53
     */
    public static boolean verifier(String token){
        Algorithm alg = Algorithm.HMAC256(TOKEN_SECRET);
        // 2 验证Token
        JWTVerifier verifier = JWT.require(alg)
                .build();
        try{
            verifier.verify(token);
            log.info("token验证成功!");
            return true;
        } catch (JWTVerificationException e) {
            log.debug("token验证失败!");
            return false;
        }
    }

    /**
     * @description 获取token的内容
     * @author LiuChangLan
     * @since 2020/6/29 15:57
     */
    public static JwtPayload decrypt(String token){
        DecodedJWT originToken = JWT.decode(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(String.format("token签发时间%s",DatePattern.NORM_DATETIME_FORMAT.format(originToken.getIssuedAt())));
        String publicClaimExample = originToken.getClaim("loginUser").asString();
        JwtPayload jwtPayload = JSON.parseObject(publicClaimExample, JwtPayload.class);
        return jwtPayload;
    }

    /**
     * 获取当前登录用户的JwtPayload
     */
    public static JwtPayload getCurrentUserJwtPayload() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return decrypt(request.getHeader(DataGlobalVariable.HEADER_ACCCESS_TOKEN));
    }


}
