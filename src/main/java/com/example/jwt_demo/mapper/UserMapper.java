package com.example.jwt_demo.mapper;

import com.example.jwt_demo.pojo.Permission;
import com.example.jwt_demo.pojo.Role;
import com.example.jwt_demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("")
    public User findByUsername(String username);
    //@Select("")
    public int fingUserIdByName(String username) ;
    @Select("select role_id from user_role where user_id=#{userId}")
    public int findRoleIdByUserId(Integer userId) ;
    @Select("")
    public Role findRolesByRoleId(int roleId) ;
    public Permission findRolesByName() ;
    @Select("select * from user where name=#{username} and password=#{password}")
    public User fingUserByPwdName(Map map);
    @Delete("update user set token='' where name=#{username} and password=#{password}")
    int deleteToken(Map userPandU);
    @Update("update user set token=#{token} where name=#{username} and password=#{password}")
    int updateToken(HashMap<Object, Object> uAndP);

    Permission findPermissionByNameAndPwd(Map map);
    @Select(" select * from permission where id=(select permission_id from role_permission where role_id=#{roleId}) ")
    Permission findPermissionByroleId(int roleId);
}
