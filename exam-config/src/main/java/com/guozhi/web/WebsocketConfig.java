package com.guozhi.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @description 自动注入websocket
 * @author LiuChangLan
 * @since 2020/11/4 15:51
 */
@Configuration
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter config(){
        return new ServerEndpointExporter();
    }

}
