package com.netease.shijin.yitao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.exception.ParameterException;
import com.netease.shijin.yitao.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录功能，当用户第一次使用第三方账号登录时，创建用户，否则更新用户信息
     * @param nickName 用户昵称
     * @param accountID 用户第三方账号的ID，唯一的标识
     * @param iconURL 用户第三方头像的链接
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseBean login(@RequestBody Map<String, String> param) throws Exception {
        ResponseBean response = new ResponseBean();
        String accountID = param.get("accountID");
        String nickName = param.get("nickName");
        String iconURL = param.get("iconURL");
        if(accountID == null || accountID == "" || nickName == null || nickName == ""){
            throw new ParameterException();
        }
        String userID = userService.login(accountID, nickName, iconURL);
        response.setCode(200);
        Map<String, String> result = new HashMap<String, String>();
        result.put("userID", userID);
        response.setData(result);
        return response;
    }

    /**
     * 修改用户信息，该功能暂不提供
     * @param userID
     * @param nickName
     * @param address
     * @return
     */
//    public @ResponseBody
//    ResponseBean modifyInfo(@RequestParam long userID, @RequestParam String nickName, @RequestParam String address) {
//        ResponseBean response = new ResponseBean();
//        userService.modifyInfo(userID, nickName, address);
//        response.setCode(200);
//        return response;
//    }

    /**
     * 用户推出登录，目前只是客户端退出，后台不做操作，后期可能加上session操作
     * @param userID
     * @return
     */
    @RequestMapping(value = "/logout")
    public @ResponseBody
    ResponseBean logout(@RequestParam long userID) {
        ResponseBean response = new ResponseBean();
        response.setCode(200);
        return response;
    }
}
