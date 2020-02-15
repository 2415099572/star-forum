package com.starforum.user.service;

import com.starforum.user.dao.UserDao;
import com.starforum.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;


    public List<User> findAll(){
        return userDao.findAll();
    }

    public User login(User user) {
        User user_ = userDao.findUserByMobile(user);
        if(user_ != null && user_.getPassword().equals(user.getPassword())){
            return user_;
        }
        return null;
    }

}