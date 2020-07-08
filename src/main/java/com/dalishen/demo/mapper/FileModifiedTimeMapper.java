package com.dalishen.demo.mapper;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.bean.OpLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileModifiedTimeMapper {

    Long batchInsert(@Param("list") List<FileModifiedTimeBean> fileModifiedTimeBeanList);




}
