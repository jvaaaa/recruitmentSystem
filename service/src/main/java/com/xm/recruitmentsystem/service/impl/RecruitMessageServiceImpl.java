package com.xm.recruitmentsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xm.model.RecruitMessage;
import com.xm.recruitmentsystem.mapper.RecruitMessageMapper;
import com.xm.recruitmentsystem.service.RecruitMessageService;
import org.springframework.stereotype.Service;

@Service
public class RecruitMessageServiceImpl extends ServiceImpl<RecruitMessageMapper, RecruitMessage> implements RecruitMessageService {
}
