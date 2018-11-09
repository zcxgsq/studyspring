package com.zcx.service;
import com.zcx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcx.dao.UserDao;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User findById(String id) {
        return userDao.findById(id);
    }
}
