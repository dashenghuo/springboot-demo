package com.dalishen.demo.controller;

import com.dalishen.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author dasheng
 * @Date 2020/1/5 12:29
 */

@RestController
@Slf4j
public class Hellocontroller {


    @Autowired
    @Resource(name = "stringRedisTemplate1")
    private StringRedisTemplate redis1RedisTemplate;



    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request, HttpServletResponse response) {

        request.getSession().getAttribute("account");

        redis1RedisTemplate.opsForValue().set("dalishen:1", account + 12345645);

        redis1RedisTemplate.opsForValue().get("dalishen:1");
        String openapi = request.getContextPath();
        String method = request.getMethod();
        String uri = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();

        log.info("login, openapi:[{}], method:[{}], uri:[{}], ", openapi, method, uri);
        return ResultVO.buildSuccessResult("hello world:" + account + password);

    }

}
