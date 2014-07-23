package com.netease.shijin.yitao.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.netease.shijin.yitao.exception.ParameterException;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.service.MarkService;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private MarkService markService;

    @RequestMapping(value = "/itemlist", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean getItemList(@RequestBody QueryRequestBean query) throws Exception {
        ResponseBean response = new ResponseBean();
        List<Map<String, Object>> result = itemService.queryItem(query);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public @ResponseBody
    ResponseBean getItemDetail(@RequestParam(required = false) String userID, @RequestParam String itemID) throws Exception {
        if (itemID == null || itemID == "") {
            throw new ParameterException();
        }
        ResponseBean response = new ResponseBean();
        Map<String, Object> data = new HashMap<String, Object>();
        ItemDetailBean itemDetail = itemService.getItemDetail(itemID);
        data.put("item", itemDetail);
        if (userID != null) {
            boolean isMarked = markService.isMarked(userID, itemID);
            data.put("isMarked", isMarked == true ? 1 : 0);
        }
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean addItem(@RequestBody Map param, HttpServletRequest request) {
        ResponseBean response = new ResponseBean();
        // 解析参数
        ItemDetailBean itemDetail = new ItemDetailBean();
        itemDetail.setExpiredTime(new Timestamp((long) param.get("expiredTime")));
        itemDetail.setPublishTime(new Timestamp((long) param.get("publishTime")));
        itemDetail.setItemAddress((String) param.get("itemAddress"));
        itemDetail.setItemCategory(Integer.valueOf((String) param.get("itemCategory")));
        itemDetail.setItemDegree(Integer.valueOf((String) param.get("itemDegree")));
        itemDetail.setItemName((String) param.get("itemName"));
        itemDetail.setItemDescription((String) param.get("itemDescription"));
        itemDetail.setItemPrice(Double.valueOf((String) param.get("itemPrice")));
        itemDetail.setImgURL((String) param.get("imgURL"));
        itemDetail.setPositionX(Double.valueOf((String) param.get("positionX")));
        itemDetail.setPositionY(Double.valueOf((String) param.get("positionY")));
        itemDetail.setSellerID((String) param.get("sellerID"));
        itemDetail.setSellerName((String) param.get("sellerName"));
        itemDetail.setSellerTel((String) param.get("sellerTel"));

        boolean result = itemService.addItem(itemDetail);
        response.setCode(200);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/myitem", method = RequestMethod.GET)
    public @ResponseBody
    ResponseBean getMyItem(@RequestParam String userID, @RequestParam int page, @RequestParam int count) throws Exception {
        if (userID == null || userID == "" || page <= 0 || count < 0) {
            throw new ParameterException();
        }
        ResponseBean response = new ResponseBean();
        List<ItemBean> result = itemService.queryMyItem(userID, page, count);
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for (ItemBean item : result) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("item", item);
            items.add(map);
        }
        response.setCode(200);
        response.setData(items);
        return response;
    }

    @RequestMapping(value = "/offshelve", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    ResponseBean offShelve(@RequestBody BaseRequestBean requestParam) throws Exception {
        ResponseBean response = new ResponseBean();
        // TODO
        String userID = requestParam.getUserID();
        String itemID = requestParam.getItemID();
        if(userID == null || userID == "" || itemID == null || itemID == ""){
            throw new ParameterException();
        }
        boolean result = itemService.offShelve(userID, itemID);
        response.setCode(200);
        response.setData(result);
        return response;
    }
}
