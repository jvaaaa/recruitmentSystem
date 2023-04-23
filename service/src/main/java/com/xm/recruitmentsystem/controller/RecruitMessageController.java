package com.xm.recruitmentsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xm.common.jwt.JwtUtils;
import com.xm.common.result.Result;
import com.xm.model.Company;
import com.xm.model.RecruitMessage;
import com.xm.recruitmentsystem.mapper.CompanyMapper;
import com.xm.recruitmentsystem.mapper.RecruitMessageMapper;
import com.xm.recruitmentsystem.service.CompanyService;
import com.xm.recruitmentsystem.service.RecruitMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/recruitMessage")
public class RecruitMessageController {

    @Autowired
    private RecruitMessageService recruitMessageService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RecruitMessageMapper recruitMessageMapper;

    @PostMapping("/submit")
    public Result submit(@RequestHeader(value = "token") String token, @RequestBody RecruitMessage recruitMessage){
        String openid = JwtUtils.getOpenId(token);
        Integer companyId = companyMapper.getId(openid);
        recruitMessage.setCompanyId(companyId);
        recruitMessageService.save(recruitMessage);
        return Result.ok();
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<RecruitMessage> list = recruitMessageService.list();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        return Result.ok(resultMap);
    }

    @GetMapping("/getList")
    public Result getList(@RequestParam("pageNum")Integer pageNum){
        Page<RecruitMessage> page = new Page<>(pageNum,5);
        Page<RecruitMessage> recruitMessagePage = recruitMessageService.page(page, null);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("page",recruitMessagePage);
        return Result.ok(resultMap);
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam("id")Integer id){
        RecruitMessage recruitMessage = recruitMessageMapper.getOneById(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("recruitMessage",recruitMessage);
        return Result.ok(resultMap);
    }

}
