package com.xm.recruitmentsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.model.RecruitMessage;

public interface RecruitMessageMapper extends BaseMapper<RecruitMessage> {

    RecruitMessage getOneById(Integer id);

}
