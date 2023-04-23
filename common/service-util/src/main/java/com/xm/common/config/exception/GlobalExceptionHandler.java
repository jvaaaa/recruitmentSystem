package com.xm.common.config.exception;

import com.xm.common.result.Result;
import com.xm.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public Result TokenError(TokenException e){
        e.printStackTrace();
        return Result.build(null,ResultCodeEnum.LOGIN_AUTH);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("出现异常?");
    }

}
