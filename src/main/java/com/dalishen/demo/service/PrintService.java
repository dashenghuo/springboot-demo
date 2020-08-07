package com.dalishen.demo.service;

import com.dalishen.demo.bean.OpLog;

import java.util.Date;

public interface PrintService {

    String print(String account,String password);

    Long insertOpLog(String module, String content, String ip, String result, Date logDate, String creator);

    OpLog selectOpLogByid(Long id);

    Long deleteByPrimaryKey(Long id);



}
