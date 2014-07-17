package com.netease.shijin.yitao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/itemlist", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean getItemList(@RequestBody QueryRequestBean query) {
        ResponseBean response = new ResponseBean();
        List<ItemBean> result = itemService.queryItem(query);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public @ResponseBody
    ResponseBean getItemDetail(String userID, String itemID) {
        ResponseBean response = new ResponseBean();
        //TODO
        return response;
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean addItem() {
        ResponseBean response = new ResponseBean();
        //TODO
        return response;
    }
    
    public @ResponseBody ResponseBean getMyItem(){
        ResponseBean response = new ResponseBean();
        //TODO
        return response;
    }
    
    public @ResponseBody ResponseBean pulloffItem(){
        ResponseBean response = new ResponseBean();
        //TODO
        return response;
    }
}
