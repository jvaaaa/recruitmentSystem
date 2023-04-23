package com.xm.recruitmentsystem.config;

import com.xm.recruitmentsystem.interceptor.TokenAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    //允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE","OPTIONS")
                .maxAge(3600);
    }


    //添加权限拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] excludePath = new String[]{"/error","/api/wxuserinfo/login","/recruitMessage/getList"};

        registry.addInterceptor(new TokenAuthenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }
}
