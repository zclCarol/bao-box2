package com.bao.auth.model;

import lombok.Data;

@Data
public class UserLoginForm {
    private String username;
    private String userId;
    private String password;
    private String captcha;
    private String uuid;
}
