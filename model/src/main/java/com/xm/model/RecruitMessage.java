package com.xm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xm.typehander.ListToVarcharTypeHandler;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_recruitmessage")
public class RecruitMessage {

    @TableId(type = IdType.AUTO)
    @TableField(select = false)
    private Integer id;

    @TableField(select = false)
    private Integer companyId;

    private String profession;

    private Integer minPersonNum;//最低招聘人数

    private Integer maxPersonNum;//最高招聘人数

    private Integer salary;//薪资

    @TableField(typeHandler = ListToVarcharTypeHandler.class)
    private List<String> detail;//细节

    @TableField(exist = false)
    private Company company;

}
