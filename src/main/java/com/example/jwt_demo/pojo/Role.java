package com.example.jwt_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:42
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    //id编号
    private int id;
    //父id
    private int parent_id;
    //角色唯一的code编码
    private String code;
    //角色名
    private String name;
    //角色介绍
    private String intro;
    //创建时间
    private Date created;
    //创建人
    private String creator;
    //修改时间
    private Date edited;
    //修改人
    private String editor;
    //deleted删除
    private int deleted;


}
