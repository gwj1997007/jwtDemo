package com.example.jwt_demo.serviceImp;

import com.example.jwt_demo.mapper.UserMapper;
import com.example.jwt_demo.pojo.Permission;
import com.example.jwt_demo.pojo.Role;
import com.example.jwt_demo.pojo.User;
import com.example.jwt_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:22
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:22
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public Permission findPermissionByNameAndPwd(Map map) {
        //user---user_role---role---role_permission
        User user = userMapper.fingUserByPwdName(map);
        int roleId = userMapper.findRoleIdByUserId(user.getId());
        Permission permissions = userMapper.findPermissionByroleId(roleId);
        return permissions;
    }

    @Override
    public User fingUserByPwdName(Map map) {
        return userMapper.fingUserByPwdName(map);
    }

    @Override
    public String findTokenByusAndPas(HashMap<Object, Object> message) {
        return null;
    }

    @Override
    public int updateUserToken(HashMap<Object, Object> uAndP, String level) {
       int temp = 0;
        switch (level){
            case "删除":temp=userMapper.deleteToken(uAndP); break;
            case "修改":temp=userMapper.updateToken(uAndP); break;
        }
        return temp;
    }


}
