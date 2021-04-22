package com.example.sellersystem.controller;

import com.example.sellersystem.apiRequest.LoginUserReq;
import com.example.sellersystem.apiResponse.LoginResp;
import com.example.sellersystem.server.LoginServer;
import com.example.sellersystem.server.RegisterServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {

    private final LoginServer loginServer;

    @Autowired  //构造时进行依赖注入
    public LoginController(LoginServer loginAndRegister, RegisterServer registerServer) {
        this.loginServer = loginAndRegister;
    }


    @PostMapping("/login")
    public LoginResp login(@RequestBody LoginUserReq userReq) {
        log.info("用户登陆 POST Request:/login -------- [前端传入：{}]", userReq);

        LoginResp result = loginServer.login(
                userReq.getUserID(),
                userReq.getPassword()
        );

        log.info("用户登录 POST Response:/login -------- [后端返回：{}]", result);
        return result;
    }

}
