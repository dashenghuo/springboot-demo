package com.dalishen.demo.bean;


import lombok.Data;

import java.util.Date;

@Data
public class OpLog {
    private Long id;

    private String module;

    private String content;

    private String ip;

    private String result;

    private Date logDate;

    private String creator;

}