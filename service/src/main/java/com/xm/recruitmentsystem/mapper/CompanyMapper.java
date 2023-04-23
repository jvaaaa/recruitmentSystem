package com.xm.recruitmentsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xm.model.Company;
import org.apache.ibatis.annotations.Select;

public interface CompanyMapper extends BaseMapper<Company> {

    @Select("select id from t_company where openid = #{openid}")
    Integer getId(String openid);
}
