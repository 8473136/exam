package com.guozhi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.common.JwtPayload;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author LiuchangLan
 * @date 2020/7/16 15:04
 */
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
}
