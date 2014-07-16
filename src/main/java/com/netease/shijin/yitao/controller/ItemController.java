package com.netease.shijin.yitao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/itemList")
    public @ResponseBody ResponseBean getItemList(@RequestBody QueryBean query){
        ResponseBean response = new ResponseBean();
        List<ItemBean> result = itemService.queryItem(query);
        response.setCode(200);
        response.setData(result);
        return response;
    }
    
}
