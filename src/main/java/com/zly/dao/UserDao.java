package com.zly.dao;

import com.zly.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    // 根据用户名查找用户
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
    // 添加用户
    @Insert("insert into user(username,password) values(#{username},#{password})")
    void addUser(User user);
}
