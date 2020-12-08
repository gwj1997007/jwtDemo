package com.example.jwt_demo.controller;

import com.example.jwt_demo.pojo.Role;
import com.example.jwt_demo.pojo.User;
import com.example.jwt_demo.service.UserService;
import com.example.jwt_demo.util.JwtUtil;
import com.example.jwt_demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:19
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:19
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //注入service
    @Autowired
    private UserService userService;

    /**
     * 方法描述
     * 登录时传参问题要隐蔽
     * 接收json对象使用@RequestBody注解并且引入依赖
     * <dependency>
     * <groupId>com.fasterxml.jackson.core</groupId>
     * <artifactId>jackson-databind</artifactId>
     * <version>2.9.8</version>
     * </dependency>
     * 登录生成token
     *
     * @return
     * @author qqg
     * @date 2020/12/7
     */
    @RequestMapping("/login")
    public JsonResult login(@RequestBody User user) {
        String token;
        try {
            //调用service
            HashMap<Object, Object> UAndP = new HashMap<>();
            String username = user.getName();
            String password = user.getPassword();
            UAndP.put("username", user.getName());
            UAndP.put("password", user.getPassword());
            //根据用户名密码查找有无此人
            User user1 = userService.fingUserByPwdName(UAndP);
            //判断密码
            if (user1 != null) {
                //查找此人生成token
                token = JwtUtil.generateToken(username, password, new Date());
                UAndP.put("token", token);
                //将token存储在user表中
                int effectRows = userService.updateUserToken(UAndP, "修改");
            } else {
                return JsonResult.fail("查无此人请先注册");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok(token);
    }


    @RequestMapping("/logout")
    public JsonResult logout(String username, String password) {

        //调用service
        HashMap<Object, Object> UAndP = new HashMap<>();
        UAndP.put("username", username);
        UAndP.put("password", password);
        //删除user的token信息
        int effectRows = userService.updateUserToken(UAndP, "删除");
        return JsonResult.ok(UAndP);

    }

    @RequestMapping("/unAuth")
    public JsonResult UnAuth() {
        System.out.println("进入了无权限方法");
        return JsonResult.fail("权限不足");
    }


    /**
     * 方法描述
     * 其他接口用于测是权限
     *
     * @return
     * @author qqg
     * @date 2020/12/8
     */
    @RequestMapping(value = "/hello", name = "sys::higer")
    public JsonResult testOther() {
        return JsonResult.ok("有权限访问");
    }


}
