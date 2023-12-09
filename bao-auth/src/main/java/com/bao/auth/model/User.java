package com.bao.auth.model;

import lombok.Data;

@Data
public class User {
    private Long no;
    private String deptId;
    private String userId;
    private String userName;
    private String userPassword;
    private Boolean userStatus;
}
