package com.dalishen.demo.service;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.bean.OpLog;

import java.util.Date;
import java.util.List;

public interface FileModifiedTimeService {

    /**
     * 将bean存储到 Mysql
     * @param fileModifiedTimeBeanList
     * @param creator
     * @return
     */
    Long save2Mysql(List<FileModifiedTimeBean> fileModifiedTimeBeanList, String creator);


    /**
     * 读取 某个目录下的文件的信息转成  bean
     * @param rootPath  某个目录
     * @return
     */
    List<FileModifiedTimeBean> getFileModifiedTimeBeanFrom(String rootPath);

}