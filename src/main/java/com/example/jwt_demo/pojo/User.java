package com.example.jwt_demo.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 13:26
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 13:26
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //id
    private int id;
    //state用户状态
    private int state;
    //name姓名
    private String name;
    //head_img_url头像图片地址
    private String head_img_url;
    //mobile电话号码
    private String mobile;
    //salt盐
    private String salt;
    //password
    private String password;
    //created创建时间--后台传前台
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date created;
    //creator创建人
    private String creator;
    //edited修改时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date edited;
}
