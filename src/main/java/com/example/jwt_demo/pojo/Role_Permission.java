package com.example.jwt_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 15:09
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 15:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role_Permission {
//id
    private int id;
    //角色id
    private int role_id;
    //权限id
    private int permission;
    //创建时间
    private Date created;
    //创建人
    private String creator;
    //修改时间
    private Date edited;
    //删除
    private int deleted;
}
