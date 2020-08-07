package com.dalishen.demo.mapper;

import com.dalishen.demo.bean.OpLog;
import com.dalishen.demo.bean.UserUri;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

//    Long insert(@Param("opLog") OpLog opLog);
//
//    OpLog selectOpLogById(@Param("id") Long id);

    List<UserUri> selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
