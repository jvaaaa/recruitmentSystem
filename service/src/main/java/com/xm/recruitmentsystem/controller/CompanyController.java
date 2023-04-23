package com.xm.recruitmentsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xm.common.jwt.JwtUtils;
import com.xm.common.result.Result;
import com.xm.model.Company;
import com.xm.recruitmentsystem.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //查询是否绑定公司
    @GetMapping("/check")
    public Result checkCompany(@RequestHeader(value = "token") String token){
        String openid = JwtUtils.getOpenId(token);
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("openid", openid));
        if(company == null){
            return Result.ok("false");
        }else {
            return Result.ok("true");
        }
    }

    //提交公司信息
    @PostMapping("/submit")
    public Result submit(@RequestHeader(value = "token") String token,@RequestBody Company company){
        String openid = JwtUtils.getOpenId(token);
        company.setOpenid(openid);
        Company resultCompany = companyService.getOne(new QueryWrapper<Company>().eq("openid", openid));
        if(resultCompany != null){
            companyService.update(company,new QueryWrapper<Company>().eq("openid",openid));
        }else {
            companyService.save(company);
        }
        return Result.ok();
    }

    @GetMapping("/get")
    public Result getCompany(@RequestHeader(value = "token") String token){
        String openid = JwtUtils.getOpenId(token);
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("openid", openid));
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("company",company);
        return Result.ok(resultMap);
    }


}
