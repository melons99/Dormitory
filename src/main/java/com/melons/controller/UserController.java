package com.melons.controller;

import com.melons.mapper.UserMapper;
import com.melons.pojo.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    public void fuck(){
        System.out.println(userMapper);
    }


    }




