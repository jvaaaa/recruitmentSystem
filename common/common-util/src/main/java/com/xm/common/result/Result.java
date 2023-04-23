package com.xm.common.result;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;//状态码
    private String message;//返回信息
    private T data;//数据

    //封装返回数据
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();

        // 封装数据
        if (body!=null){
            result.setData(body);
        }

        // 状态码
        result.setCode(resultCodeEnum.getCode());
        //返回信息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    // 构造私有化
    private Result(){

    }

    //成功无数据
    public static<T> Result<T> ok(){
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //成功有数据
    public static<T> Result<T> ok(T data){
        return Result.build(data,ResultCodeEnum.SUCCESS);
    }

    //失败无数据
    public static<T> Result<T> fail(){
        return Result.build(null,ResultCodeEnum.FAIL);
    }

    //失败有数据
    public static<T> Result<T> fail(T data){
        return Result.build(data,ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }


}
