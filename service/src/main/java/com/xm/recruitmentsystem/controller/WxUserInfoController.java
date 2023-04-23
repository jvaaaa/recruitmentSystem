package com.xm.recruitmentsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xm.common.jwt.JwtUtils;
import com.xm.common.result.Result;
import com.xm.common.http.HttpClientUtil;
import com.xm.model.WxUserInfo;
import com.xm.recruitmentsystem.properties.WeixinProperties;
import com.xm.recruitmentsystem.service.WxUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/wxuserinfo")
public class WxUserInfoController {

    //注入wxUserInfoService
    @Autowired
    private WxUserInfoService wxUserInfoService;

    @Autowired
    private WeixinProperties weixinProperties;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @PostMapping("/login")
    public Result login(@RequestBody WxUserInfo wxUserInfo){
        //根据code获取openid
        String url = weixinProperties.getJscode2sessionUrl()+"?appid="+weixinProperties.getAppid()+"&secret="+weixinProperties.getSecret()+"&js_code="+wxUserInfo.getCode()+"&grant_type=authorization_code";
        String result = httpClientUtil.sendHttpGet(url);
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.get("openid").toString();

        //根据openid查找用户
        WxUserInfo resultWxUserInfo = wxUserInfoService.getById(openid);

        //不存在用户 进行注册处理
        if(resultWxUserInfo == null){
           wxUserInfo.setOpenid(openid);
           wxUserInfo.setRegisterDate(new Date());
           wxUserInfo.setLastLoginDate(new Date());
           wxUserInfoService.save(wxUserInfo);
        }else {
            //存在用户 进行更新数据
            resultWxUserInfo.setNickName(wxUserInfo.getNickName());
            resultWxUserInfo.setAvatarUrl(wxUserInfo.getAvatarUrl());
            resultWxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.updateById(resultWxUserInfo);
        }

        //生成token
        String token = JwtUtils.createToken(openid,wxUserInfo.getNickName());

        //返回token
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("token",token);
        return Result.ok(resultMap);
    }

}
