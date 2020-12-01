package com.guozhi.websocket;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.guozhi.web.MyEndpointConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 考试人数在线心跳监听
 * @author LiuchangLan
 * @date 2020/11/2 15:48
 * paperId 试卷id
 * userId 用户id
 * equipment 设备名称
 */
@ServerEndpoint(value = "/ExamNow/{paperId}/{userId}/{client}", configurator = MyEndpointConfigure.class)
@Component
@Slf4j
public class ExamNow {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description 存放所有在线的客户端 static保证线程安全
     * @author LiuChangLan
     * @since 2020/8/7 10:03
     */
    private static Map<Integer, Session> clients = new ConcurrentHashMap<>();

    /**
     * @description 连接
     * @author LiuChangLan
     * @since 2020/8/7 10:09
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId, @PathParam("paperId") Integer paperId,@PathParam("client") String client) {
        // key
        String key = "Exam" + userId + paperId;

        Map<String,Object> jsonObj = new HashMap<>();
        // 用户id
        jsonObj.put("userId",userId);
        // 时间id
        jsonObj.put("paperId",paperId);
        // 时间
        jsonObj.put("date", DateUtil.now());
        // 在线设备
        jsonObj.put("client",client);

        stringRedisTemplate.opsForList().leftPush(key,JSON.toJSONString(jsonObj));
        clients.put(userId, session);
        // 在线设备数
        Long size = stringRedisTemplate.opsForList().size(key);
        if (size > 1){
            // 不能考试
            this.sendMessage(userId,"false");
        }else {
            this.sendMessage(userId,"true");
        }
        log.info("客户端{}连接了 当前在线人数：{}", userId, clients.size());
    }

    /**
     * @description 断开连接
     * @author LiuChangLan
     * @since 2020/8/7 10:09
     */
    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId, @PathParam("paperId") String paperId) {
        // key
        String key = "Exam" + userId + paperId;
        stringRedisTemplate.opsForList().leftPop(key);
        clients.remove(userId);
        log.info("客户端{}断开了 当前在线人数：{}", userId, clients.size());
    }

    /**
     * @description 发送错误
     * @author LiuChangLan
     * @since 2020/8/7 10:09
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * @description 接受消息
     * @author LiuChangLan
     * @since 2020/8/7 10:09
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message) {
        log.info("服务端收到客户端{}发来的消息: {}", userId, message);
    }

    public void sendMessage(Integer userId, String message) {
        Session session = clients.get(userId);
        if (session != null) {
            session.getAsyncRemote().sendText(message);
        }
    }

}
