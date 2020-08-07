package com.dalishen.demo.bean;


import lombok.Data;

import java.util.Date;

@Data
public class UserUri {
    private Long id;

    private String username;

    private Long roleId;

    private String rolename;

    private String uriAuth;

}