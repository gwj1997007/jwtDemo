package com.example.jwt_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 15:27
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 15:27
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private int id;
    //父id
    private int parent_id;
    //权限唯一code
    private String code;
    //权限名称
    private String name;
    //权限介绍
    private String intro;
    //权限类别
    private int category;
    //url规则
    private Date created;
    //创建人
    private String creator;
    //修改时间
    private Date edited;
    //修改人
    private String editor;
    //删除
    private int deleted;
}
