package com.melons.service;

import com.melons.mapper.UserMapper;
import com.melons.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper  userMapper;


    @Override
    public List<User> findAll() {
        System.out.println("--------begin--------");
        List<User> userList = userMapper.findAll();
        System.out.println("-------success-------");
        System.out.println("---------end---------");
        return userList;
    }

    @Override
    public User findUser(User user){
        return userMapper.findUser(user);
    }

    @Override
    public User loginUser(User user) {
        return null;
    }

    public void resigter(User user){
        System.out.println("--------begin--------");
//        System.out.println(userMapper.resigter(user));
        System.out.println("-------success-------");
        System.out.println("---------end---------");
//        return userMapper.resigter(user);
    }
}
