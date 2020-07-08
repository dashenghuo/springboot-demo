package com.dalishen.demo.bean;


import lombok.Data;

import java.util.Date;

@Data
public class FileModifiedTimeBean {

    private Long id;

    private String filePath;

    private String fileName;

    private Date modifiedTime;

    private Date createTime;

    private String creator;




}