package com.study.springboot.web;

import com.study.springboot.domain.UserMessage;
import com.study.springboot.domain.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;


@Controller
public class UserMsgController {
    @MessageMapping("/welcome")//类似于@RequestMapping
    @SendTo("/topic/getResponse")//当服务器端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息。
    public UserResponse say(UserMessage userMessage) throws Exception{
        Thread.sleep(3000);
        return new UserResponse("Welcome, "+userMessage.getName()+" !");
    }
    @Autowired
    private SimpMessagingTemplate messagingTemplate;//通过SimpMessagingTemplate像浏览器发送消息
    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){//在springMVC中可以直接在参数中获得principal，它当前用户的信息包含
        if (principal.getName().equals("syl")){//硬代码，只为实现双人通信功能
            messagingTemplate.convertAndSendToUser("yby","/queue/notifications",principal.getName()+"-send: "+msg);
            //向用户发送消息，第一个参数接收消息的用户，第二个浏览器订阅的地址，第三个是消息本身
        }else {
            messagingTemplate.convertAndSendToUser("syl","/queue/notifications",principal.getName()+"-send"+msg);
        }
    }
}
