package com.bao.auth.controller;

import com.bao.auth.common.R;
import com.bao.auth.model.UserLoginForm;
import com.bao.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserLoginForm form) {
        System.out.printf("123");
        R r = userService.login(form);
        return r;
    }
}
