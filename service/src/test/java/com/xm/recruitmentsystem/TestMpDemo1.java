package com.xm.recruitmentsystem;

import com.xm.model.RecruitMessage;
import com.xm.model.WxUserInfo;
import com.xm.recruitmentsystem.mapper.CompanyMapper;
import com.xm.recruitmentsystem.mapper.RecruitMessageMapper;
import com.xm.recruitmentsystem.mapper.WxUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private RecruitMessageMapper recruitMessageMapper;

    @Test
    void getAll(){
        RecruitMessage recruitMessage = recruitMessageMapper.getOneById(1);
        log.info(recruitMessage.toString());
    }

}
