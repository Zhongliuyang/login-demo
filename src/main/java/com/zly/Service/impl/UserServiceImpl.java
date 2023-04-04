package com.zly.Service.impl;

import com.zly.Service.UserService;
import com.zly.dao.UserDao;
import com.zly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
