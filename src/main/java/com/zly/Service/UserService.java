package com.zly.Service;

import com.zly.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


public interface UserService {
    // 根据用户名查找用户
    User findByUsername(String username);
    //验证密码
    // 添加用户
    void addUser(User user);
}
