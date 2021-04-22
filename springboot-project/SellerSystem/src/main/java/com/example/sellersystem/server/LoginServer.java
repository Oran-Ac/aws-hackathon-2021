package com.example.sellersystem.server;

import com.example.sellersystem.apiResponse.LoginResp;
import com.example.sellersystem.dao.ISellerUserRepo;
import com.example.sellersystem.document.SellerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServer {

    private final ISellerUserRepo userRepo;

    @Autowired  //构造时进行依赖注入
    public LoginServer(ISellerUserRepo userRepo) {
        this.userRepo = userRepo;
    }


    //验证登陆
    public LoginResp login(String userID, String password) {
        SellerUser user = userRepo.getUser(userID);
        if(null == user)
            return LoginResp.USER_NOT_FOUND;
        if(password.equals(user.getPassword()))
            return LoginResp.SUCCESS;
        return LoginResp.INCORRECT_PASSWORD;
    }

}
