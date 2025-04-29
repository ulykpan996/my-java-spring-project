package com.example.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.resourceserver.jwt.JwtDecoder;
import org.springframework.security.oauth2.resourceserver.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 处理 OAuth2 登录和资源服务器保护
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/public").permitAll() // 公开端点
                .anyRequest().authenticated() // 其他端点需要认证
                .and()
                .oauth2Login() // 启用 OAuth2 登录
                .and()
                .oauth2ResourceServer().jwt(); // 启用 OAuth2 资源服务器，解析 JWT
        return http.build();
    }

    // 设置 JWT 解码器
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://dev-toy7gwqvbesqoqqj.us.auth0.com/");
    }
}
