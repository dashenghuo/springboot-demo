package com.dalishen.demo.service.impl;

import com.dalishen.demo.bean.OpLog;
import com.dalishen.demo.bean.UserUri;
import com.dalishen.demo.mapper.ManageMapper;
import com.dalishen.demo.mapper.UserMapper;
import com.dalishen.demo.service.PrintService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class PrintServiceImpl implements PrintService {


    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Resource(name = "redisTemplate1")
    private RedisTemplate redisTemplate;

    @Override
    public String print(String account, String password) {

        List<UserUri> userUriList = userMapper.selectUserByUsernameAndPassword(account,password);
        Set uriset = new HashSet<>();
        for (UserUri userUri: userUriList) {
            uriset.add(userUri.getUriAuth());
        }
        redisTemplate.opsForSet().add(userUriList.get(0).getRoleId().toString(), uriset);
        return userUriList.get(0).getRolename();
    }

    @Override
    public Long insertOpLog(String module, String content, String ip, String result, Date logDate, String creator) {

        OpLog opLog = new OpLog();
        opLog.setModule(module);
        opLog.setContent(content);
        opLog.setIp(ip);
        opLog.setResult(result);
        opLog.setLogDate(logDate);
        opLog.setCreator(creator);
        Long oplogId = manageMapper.insert(opLog);

        log.info("opLog: [{}]", opLog.toString());
        return oplogId;
    }

    @Override
    public OpLog selectOpLogByid(Long id) {

        OpLog opLog = manageMapper.selectOpLogById(id);
        log.info("selectOpLogByid, opLog: [{}]", opLog.toString());
        return opLog;
    }

    @Override
    public Long deleteByPrimaryKey(Long id) {

        Long deleteid = manageMapper.deleteByPrimaryKey(id);
        log.info("deleteByPrimaryKey, id: [{}], deleteid: [{}]", id, deleteid);
        return deleteid;
    }


}
