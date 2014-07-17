package com.netease.shijin.yitao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.MarkService;

@Controller
public class MarkController {

    @Autowired
    private MarkService markService;
    
    public @ResponseBody ResponseBean markItem(String userID, String itemID){
        ResponseBean response = new ResponseBean();
        //TODO mark item
        return response;
    }
    
    public @ResponseBody ResponseBean deleteMarkedItem(String userID, String itemID){
        ResponseBean response = new ResponseBean();
        //TODO delete marked item
        return response;
    }
    
    public @ResponseBody ResponseBean getMarkItemList(){
        ResponseBean response = new ResponseBean();
        //TODO
        return response;
    }
}
