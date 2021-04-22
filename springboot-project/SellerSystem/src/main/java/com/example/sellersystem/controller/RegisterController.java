package com.example.sellersystem.controller;

import com.example.sellersystem.apiRequest.RegisterReq;
import com.example.sellersystem.apiResponse.RegisterResp;
import com.example.sellersystem.server.RegisterServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class RegisterController {

    private final RegisterServer registerServer;

    @Autowired
    public RegisterController(RegisterServer registerServer) {
        this.registerServer = registerServer;
    }


    @PostMapping("/register")
    public RegisterResp register(@RequestBody RegisterReq registerReq) {
        log.info("用户注册 POST Request:/register -------- [前端传入：{}]", registerReq);

        RegisterResp result = registerServer.register(
                registerReq.getUserID(),
                registerReq.getPassWord(),
                registerReq.getCountry());

        log.info("用户注册 POST Response:/register -------- [后端返回json：{}]", result);
        return result;
    }
}
