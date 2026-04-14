package com.example.labreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 配置密码加密器（这样后端才会知道用 BCrypt 去解密那串 123456 的密文）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置核心的安全拦截规则
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 开启跨域支持（解决前端 3000 端口访问后端 8080 端口的问题）
                .cors().and()
                // 2. 关闭 CSRF 保护（前后端分离项目通常需要关闭它，否则 POST 请求会被拦截）
                .csrf().disable()
                // 3. 配置拦截规则
                .authorizeRequests()
                // 👇 最关键的一步：告诉系统，登录接口不需要验证，任何人都可以访问！
                .antMatchers("/api/users/login").permitAll()
                // 其他所有的接口，都必须验证通过（登录后）才能访问
                .anyRequest().authenticated();

        return http.build();
    }
}
