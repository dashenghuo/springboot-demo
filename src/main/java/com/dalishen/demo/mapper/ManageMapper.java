package com.dalishen.demo.mapper;

import com.dalishen.demo.bean.OpLog;
import org.apache.ibatis.annotations.Param;

public interface ManageMapper {

    Long insert(@Param("opLog") OpLog opLog);

    OpLog selectOpLogById(@Param("id") Long id);

    Long deleteByPrimaryKey(Long id);

}
