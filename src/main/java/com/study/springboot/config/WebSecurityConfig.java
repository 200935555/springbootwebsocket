package com.study.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/","/login","/usermsg").permitAll()//设置SpringSecurity对"/","/login"路径不拦截。
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")//设置SpringSecurity登录页面访问的路径为/login
//                //.defaultSuccessUrl("/chat")//登录成功后转向的路径
//                .successForwardUrl("/chat")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
        super.configure(http);//使用默认配置。
    }
    //在内存中配置两个用户，角色是USER.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("syl").password("123").roles("USER")
                .and()
                .withUser("yby").password("123").roles("USER");
    }
    ///resources/static/**为静态资源，SpringSecurity不拦截。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/resources/static/**");
    }
}
