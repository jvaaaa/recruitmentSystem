package com.xm.recruitmentsystem.interceptor;

import com.xm.common.config.exception.TokenException;
import com.xm.common.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("访问路径:{}",request.getRequestURI());

        //不是映射到方法
        if(handler instanceof HandlerMethod){
            // 判断token是否为空
            String token = request.getHeader("token");

            if(token == null || token.isEmpty()){
                throw new TokenException();
            }else {
                Claims claims = null;
                // 如果token不为空 鉴权
                try {
                    claims = JwtUtils.validateJWT(token);
                }catch (Exception e){
                    throw new TokenException();
                }

                if(claims==null){
                    throw new TokenException();
                }else{
                    return true;
                }
            }
        }else{
            return true;
        }
    }
}
