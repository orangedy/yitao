package com.netease.shijin.yitao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/createAccount")
    public @ResponseBody
    ResponseBean login(@RequestParam String nickName, @RequestParam String accountID, @RequestParam String iconURL) {
        ResponseBean response = new ResponseBean();
        long userID = userService.login(accountID, nickName, iconURL);
        response.setCode(200);
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("userID", userID);
        response.setData(result);
        return response;
    }

    public @ResponseBody
    ResponseBean modifyInfo(@RequestParam long userID, @RequestParam String nickName, @RequestParam String address) {
        ResponseBean response = new ResponseBean();
        userService.modifyInfo(userID, nickName, address);
        response.setCode(200);
        return response;
    }

    public @ResponseBody
    ResponseBean logout(@RequestParam long userID) {
        ResponseBean response = new ResponseBean();
        response.setCode(200);
        return response;
    }
}
