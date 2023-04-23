package com.xm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("t_wxUserInfo")
@Data
public class WxUserInfo implements Serializable {

    @TableId
    @TableField(select = false)
    private String openid;//用户唯一标识

    private String nickName;//用户昵称

    private String avatarUrl;//用户头像图片的 URL

//    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date registerDate;//注册日期

//    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date lastLoginDate;//最后登录日期

    @TableField(exist = false)
    private String code;//微信用户code 前端传来的


}
