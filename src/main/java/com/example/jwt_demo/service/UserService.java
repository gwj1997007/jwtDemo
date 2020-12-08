package com.example.jwt_demo.service;

import com.example.jwt_demo.pojo.Permission;
import com.example.jwt_demo.pojo.Role;
import com.example.jwt_demo.pojo.User;

import java.util.HashMap;
import java.util.Map;

public interface UserService {

    Permission findPermissionByNameAndPwd(Map map);

    User fingUserByPwdName(Map map);

    String findTokenByusAndPas(HashMap<Object, Object> message);

    int updateUserToken(HashMap<Object, Object> uAndP,String level);
}
