package com.example.jwt_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:24
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:24
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    //状态
    private String status;

    //数据
    private Object data;

    //编码
    private String msg;

    //成功
    public static JsonResult ok(Object data){
        return new JsonResult("200",data,"成功");
    }

    //异常
    public static JsonResult fail(String msg){
        return new JsonResult(null,null,msg);
    }
}
