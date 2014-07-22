package com.netease.shijin.yitao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ResponseBean;
import com.netease.shijin.yitao.service.SearchService;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseBean searchItem(@RequestParam String keyword, @RequestParam int page, @RequestParam int count){
        //参数处理
        ResponseBean response = new ResponseBean();
        List<ItemBean> list = searchService.search(keyword, page, count);
        response.setCode(200);
        response.setData(list);
        return response;
    }
}
