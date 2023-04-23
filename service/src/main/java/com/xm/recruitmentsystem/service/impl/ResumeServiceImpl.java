package com.xm.recruitmentsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xm.model.Resume;
import com.xm.recruitmentsystem.mapper.ResumeMapper;
import com.xm.recruitmentsystem.service.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {

}
