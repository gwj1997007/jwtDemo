package com.example.jwt_demo.Intercepter;

import com.example.jwt_demo.pojo.Permission;
import com.example.jwt_demo.service.UserService;
import com.example.jwt_demo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.util.HashMap;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:05
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:05
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
public class JwtIntercepter extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    //调用控制器前的操作
    //如果有token则判断权限，如果没有token则跳转到登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       System.out.println("拦截了");
        //获取request中cookie中的token
        String requestToken = JwtUtil.getRequestToken(request);
        //如果token不为空则验证token
        if (requestToken!=null&&!StringUtils.isEmpty(requestToken)){


            //获取username和password
            String username = JwtUtil.getUsername(requestToken);
            String password = JwtUtil.getPassword(requestToken);
            HashMap<Object, Object> message = new HashMap<>();
            message.put("username",username);
            message.put("password",password);
            //先判断改用户名+密码下的用户是否存在token,如果不存在则说明注销了
            String OsToken = userService.findTokenByusAndPas(message);
            if(OsToken==null){
                //如果token为空则为注销或者游客，设置其可访问的路径使其产生token
                response.sendRedirect("/Jwt/user/login");
            }
            //验证信息,如果为true验证通过继续访问
            if(JwtUtil.verifyToken(requestToken, username)){
                //将解析的用户名密码存入request域
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                HashMap<Object, Object> content = new HashMap<>();
                content.put("username",username);
                content.put("password",password);
                //查询此人权限信息与注解上的值进行比对
                Permission permission = userService.findPermissionByNameAndPwd(content);
                HandlerMethod h= (HandlerMethod) handler;
                RequestMapping annotation = h.getMethodAnnotation(RequestMapping.class);
                String name = annotation.name();
                //判断是否含有访问此路径的权限
                String[] permissions = name.split("::");
                for (String item:permissions
                     ) {//如果接口上注解值含有此人拥有的权限
                    if(permission.getName().contains(item)){
                        return true;
                    }
                    //跳转到权限不足的路径
                    response.sendRedirect("/Jwt/user/unAuth");
                    return false;
                }
            }
        }else{
            //如果token为空则为游客，设置其可访问的路径使其产生token
            response.sendRedirect("/Jwt/user/login");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
