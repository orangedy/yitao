package com.netease.shijin.yitao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.BaseRequestBean;
import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.MarkService;

@Controller
@RequestMapping(value = "/mark")
public class MarkController {

    @Autowired
    private MarkService markService;
    
    @RequestMapping(value = "/addmark", method = RequestMethod.POST)
    public @ResponseBody ResponseBean markItem(@RequestBody BaseRequestBean requestParam){
        ResponseBean response = new ResponseBean();
        //TODO mark item
        String userID = requestParam.getUserID();
        String itemID = requestParam.getItemID();
        boolean result = markService.addMark(userID, itemID);
        response.setCode(200);
        response.setData(result);
        return response;
    }
    
    @RequestMapping(value = "/deletemark", method = RequestMethod.POST)
    public @ResponseBody ResponseBean deleteMarkedItem(@RequestBody BaseRequestBean requestParam){
        ResponseBean response = new ResponseBean();
        //TODO delete marked item
        String userID = requestParam.getUserID();
        String itemID = requestParam.getItemID();
        boolean result = markService.deleteMarkedItem(userID, itemID);
        response.setCode(200);
        response.setData(result);
        return response;
    }
    
    @RequestMapping(value = "/marklist", method = RequestMethod.GET)
    public @ResponseBody ResponseBean getMarkItemList(@RequestParam String userID, @RequestParam int page, @RequestParam int count){
        ResponseBean response = new ResponseBean();
        //TODO
        List<ItemBean> result = markService.getMarkList(userID, page, count);
        response.setCode(200);
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for(ItemBean item : result) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("item", item);
            items.add(map);
        }
        response.setCode(200);
        response.setData(items);
        return response;
    }
}
