package com.xm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_resume")
@Data
public class Resume {

    @TableField(select = false)
    @TableId
    private String openid;//openid

    private String name;//姓名

    private String gender;//性别

    private String birthday;//生日

    private String education;//学历

    private String type;//学历类型

    private String features;//个人介绍

}
