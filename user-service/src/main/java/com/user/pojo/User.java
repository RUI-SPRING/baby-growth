package com.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * User实体类
 * createBy：NanMing
 * createTime：2022/8/25
 */
@Data
@Builder
@TableName(value = "user")//指定表名
@ApiModel(value="user对象",description="用户对象user")
public class User implements Serializable {
     //指定自增策略
     @TableId(value = "id", type = IdType.AUTO)
     @ApiModelProperty(value = "用户表主键id",required = true)
     private Long id;

     @ApiModelProperty(value = "用户唯一id",required = true)
     private Long userId;

     @ApiModelProperty(value = "用户名",required = true)
     private String userName;

     //男1、女0
     @ApiModelProperty(value = "用户性别",required = true)
     private int sex;

     @ApiModelProperty(value = "用户手机号",required = true)
     private String phone;

     @ApiModelProperty(value = "用户额外属性表id",required = true)
     private Long extraId;

     //0：不是、1：是
     @ApiModelProperty(value = "是否是vip",required = true)
     private int isVip;

}


//@Data
//@Builder
//@TableName(value = "User")//指定表名
//public class User implements Serializable {
//    private static final long serialVersionUID = -5644799954031156649L;
//    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
//    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
//    private Integer id;
//    private String name;
//    private String sex;
//    private String pwd;
//    private String email;
//}