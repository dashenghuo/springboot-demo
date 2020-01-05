package com.dalishen.demo.service.impl;

import com.dalishen.demo.bean.OpLog;
import com.dalishen.demo.mapper.ManageMapper;
import com.dalishen.demo.service.PrintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class PrintServiceImpl implements PrintService {


    @Autowired
    private ManageMapper manageMapper;

    @Override
    public String print(String account, String password) {
        log.info("print, account:[{}], password:[{}] ", account, password);
        return "printResults";
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
