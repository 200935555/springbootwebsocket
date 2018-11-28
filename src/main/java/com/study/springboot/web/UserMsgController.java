package com.study.springboot.web;

import com.study.springboot.domain.UserMessage;
import com.study.springboot.domain.UserResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserMsgController {
    @MessageMapping("/welcome")//类似于@RequestMapping
    @SendTo("/topic/getResponse")//当服务器端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息。
    public UserResponse say(UserMessage userMessage) throws Exception{
        Thread.sleep(3000);
        return new UserResponse("Welcome, "+userMessage.getName()+" !");
    }
}
