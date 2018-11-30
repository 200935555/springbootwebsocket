package com.study.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ViewController extends WebMvcConfigurationSupport {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/usermsg").setViewName("/usermessage");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/chat").setViewName("/chat");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//如果不加上此配置，访问不到static下的静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
