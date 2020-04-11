package com.bes.gateway.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        // 通过CORS配置跨域信息
        CorsConfiguration config = new CorsConfiguration();
        // 1、允许的域，不要写,否则coolie就为无法使用了。

        config.addAllowedOrigin("http://127.0.0.1:9001");
        config.addAllowedOrigin("http://manage.leyou.com");
        config.addAllowedOrigin("http://www.leyou.com");
        config.addAllowedOrigin("http://api.leyou.com");
        config.addAllowedOrigin("http://manage.leyou.com");
        // 2、是否发送cookie信息
        config.setAllowCredentials(true);
        // 3、允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4、允许的头信息
        config.addAllowedHeader("*");
        // 5、添加映射路径我们拦截一切请求
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(corsConfigurationSource);
    }
}
