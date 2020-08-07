package com.dalishen.demo.controller;

import com.dalishen.demo.service.PrintService;
import com.dalishen.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


@RestController
@Slf4j
public class Printcontroller {

    @Autowired
    private PrintService printService;

    @GetMapping(value = "/print")
    public ResultVO login(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password,
                          HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("account", account);
        request.getSession().setAttribute("role", 1);

        String openapi = request.getContextPath();
        String method = request.getMethod();
        String uri = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();

        printService.print(account, password);
        printService.insertOpLog("module", "content", "12.12.12.12", "true", new Date(), account);
        printService.selectOpLogByid(10144L);
        printService.deleteByPrimaryKey(10150L);

        log.info("login, openapi:[{}], method:[{}], uri:[{}], ", openapi, method, uri);
        return ResultVO.buildSuccessResult("hello world:" + account + password);

    }

}
