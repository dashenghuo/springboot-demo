package com.dalishen.demo.service;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.bean.OpLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FileModifiedTimeService {

    /**
     * 将bean存储到 Mysql
     * @param fileModifiedTimeBeanList
     * @param creator
     * @return
     */
    Long save2Mysql(List<FileModifiedTimeBean> fileModifiedTimeBeanList, String creator);

    FileModifiedTimeBean selectFileModifiedTimeBeanById(Long id);

    //OpLog selectFileModifiedTimeBeanByoplogId(Long id);

    Map selectFileModifiedTimeBeanByFileName(Long id, String filename);
    /**
     * 读取 某个目录下的文件的信息转成  bean
     * @param rootPath  某个目录
     * @return
     */
    List<FileModifiedTimeBean> getFileModifiedTimeBeanFrom(String rootPath);

}
