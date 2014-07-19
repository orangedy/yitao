package com.netease.shijin.yitao.controller;

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
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.service.MarkService;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    private MarkService markService;

    @RequestMapping(value = "/itemlist", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean getItemList(@RequestBody QueryRequestBean query) {
        ResponseBean response = new ResponseBean();
        List<Map<String, Object>> result = itemService.queryItem(query);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public @ResponseBody
    ResponseBean getItemDetail(@RequestParam(required = false) String userID, @RequestParam String itemID) {
        ResponseBean response = new ResponseBean();
        Map<String, Object> data = new HashMap<String, Object>();
        ItemDetailBean itemDetail = itemService.getItemDetail(itemID);
        data.put("detail", itemDetail);
        if (userID != null) {
            boolean isMarked = markService.isMarked(userID, itemID);
            data.put("isMarked", isMarked);
        }
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean addItem(@RequestBody ItemDetailBean itemDetail) {
        ResponseBean response = new ResponseBean();
        boolean result = itemService.addItem(itemDetail);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/myitem", method = RequestMethod.GET)
    public @ResponseBody
    ResponseBean getMyItem(@RequestParam String userID, @RequestParam int page, @RequestParam int count) {
        ResponseBean response = new ResponseBean();
        List<ItemBean> result = itemService.queryMyItem(userID, page, count);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/offshelve", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean offShelve(@RequestBody BaseRequestBean requestParam) {
        ResponseBean response = new ResponseBean();
        // TODO
        String userID = requestParam.getUserID();
        String itemID = requestParam.getItemID();
        boolean result = itemService.offShelve(userID, itemID);
        return response;
    }
}
