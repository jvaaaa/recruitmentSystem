package com.xm.recruitmentsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xm.common.jwt.JwtUtils;
import com.xm.common.result.Result;
import com.xm.model.Resume;
import com.xm.recruitmentsystem.service.ResumeService;
import com.xm.recruitmentsystem.service.impl.ResumeServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @GetMapping("/check")
    public Result getAll(){
        List<Resume> list = resumeService.list();
        return Result.ok(list);
    }

    //获取resume
    @GetMapping("/get")
    public Result get(@RequestHeader(value = "token")String token){
        String openid = JwtUtils.getOpenId(token);
        Resume resume = resumeService.getById(openid);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resume",resume);
        return Result.ok(resultMap);
    }

    @GetMapping("/getName")
    public Result getName(@RequestHeader(value = "token")String token){
        String openId = JwtUtils.getOpenId(token);
        Resume resultResume = resumeService.getById(openId);
        if (resultResume == null){
            return Result.ok();
        }else {
            return Result.ok(resultResume.getName());
        }
    }

    @PostMapping("/submit")
    public Result submit(@RequestHeader(value = "token")String token,@RequestBody Resume resume){
        String openid = JwtUtils.getOpenId(token);
        resume.setOpenid(openid);
        Resume resultResume = resumeService.getById(openid);
        if(resultResume != null){
            resumeService.updateById(resume);
        }else {
            resumeService.save(resume);
        }
        return Result.ok();
    }


    //获取日期
    @GetMapping("/date")
    public Result date(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        List<String> yearList = new ArrayList<>();
        for(int i = year-1;i >= 1922;i--){
            yearList.add(i+"年");
        }
        List<String> monthList = new ArrayList<>();
        for(int i = 1;i <= 12;i++){
            monthList.add(i+"月");
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("yearList",yearList);
        resultMap.put("monthList",monthList);
        return Result.ok(resultMap);
    }

}
