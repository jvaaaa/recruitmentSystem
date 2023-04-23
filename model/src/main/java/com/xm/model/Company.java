package com.xm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_company")
public class Company {

    @TableField(select = false)
    @TableId(type = IdType.AUTO)
    private Integer id;//公司ID

    @TableField(select = false)
    private String openid;//绑定者openid

    private String companyName;//公司姓名

    private String address;//公司地址

}
