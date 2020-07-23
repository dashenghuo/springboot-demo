package com.dalishen.demo.mapper;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.bean.OpLog;
import com.dalishen.demo.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileModifiedTimeMapper {

    Long batchInsert(@Param("list") List<FileModifiedTimeBean> fileModifiedTimeBeanList);

    FileModifiedTimeBean selectFileModifiedTimeBeanById(@Param("id") Long id);

    FileModifiedTimeBean selectFileModifiedTimeBeanByFileName(@Param("fileName") String fileName);

//    FileModifiedTimeBean selectFileModifiedTimeBeanByoplogId(@Param("oplogId") Long oplogId);




}
