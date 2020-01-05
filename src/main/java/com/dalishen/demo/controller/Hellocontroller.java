package com.dalishen.demo.controller;

import com.dalishen.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request, HttpServletResponse response) {
        String openapi = request.getContextPath();
        String method = request.getMethod();
        String uri = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();

        log.info("login, openapi:[{}], method:[{}], uri:[{}], ", openapi, method, uri);
        return ResultVO.buildSuccessResult("hello world:" + account + password);

    }

}
