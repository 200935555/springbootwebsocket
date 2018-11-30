package com.study.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//开启STOMP协议，传输基于代理的消息(messageBroker)，这时controller使用@MessageMapping就像使用@RequestMapping一样。
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){//注册STOMP协议的节点endpoint，并映射指定的url。
        registry.addEndpoint("/endpointUser").withSockJS();//注册一个STOMP的endpoint，并指定使用SockJS协议。
        registry.addEndpoint("/endpointChat").withSockJS();//注册一个/endpointChat的endpoint
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(MessageBroker)
        registry.enableSimpleBroker("/topic","/queue");//广播式应配置一个/topic消息代理。点对点式增加一个/queue代理
    }
}
