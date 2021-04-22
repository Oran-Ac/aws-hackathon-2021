package com.example.sellersystem.server;

import com.example.sellersystem.apiResponse.RegisterResp;
import com.example.sellersystem.dao.ISellerUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServer {

    private final ISellerUserRepo userRepo;

    @Autowired //构造时进行依赖注入
    public RegisterServer(ISellerUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //注册用户
    public RegisterResp register(String userID, String password, String country) {
        if(null == userRepo.getUser(userID)) {
            userRepo.insertUser(userID, password, country);
            return RegisterResp.REGISTER_SUCCESS;
        } else {   //用户名已经注册
            return RegisterResp.USER_FOUND;
        }
    }
}
