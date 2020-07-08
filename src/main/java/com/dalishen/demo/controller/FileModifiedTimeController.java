package com.dalishen.demo.controller;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.service.FileModifiedTimeService;
import com.dalishen.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author dasheng
 * @Date 2020/1/5 12:29
 */

@RestController
@Slf4j
public class FileModifiedTimeController {

    @Autowired
    private FileModifiedTimeService fileModifiedTimeService;


    /**
     * 将一个文件夹下的文件信息存入 mysql
     * @param creator       创建者
     * @param request       web request
     * @param response
     * @return
     */
    @GetMapping(value = "/save2Mysql")
    public ResultVO save2Mysql(@RequestParam("creator") String creator, HttpServletRequest request, HttpServletResponse response) {


        log.info("save2Mysql, creator:[{}]", creator);
        String rootPath = "D:\\work-doc\\ref\\fleaf\\WEB-INF\\classes";
        List<FileModifiedTimeBean>  fileModifiedTimeBeanList = fileModifiedTimeService.getFileModifiedTimeBeanFrom(rootPath);
        Long insertNum = fileModifiedTimeService.save2Mysql(fileModifiedTimeBeanList, creator);


        return ResultVO.buildSuccessResult("insertNum:" + insertNum);

    }

}
