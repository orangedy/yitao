package com.netease.shijin.yitao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.dao.SearchDao;
import com.netease.shijin.yitao.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private ItemDao itemDao;
    @Override
    public List<ItemBean> search(String keyword, int page, int count) {
        int start = (page - 1)*count;
//        keyword = "'%" + keyword + "%'";
        return itemDao.searchItem(keyword, start, count);
    }

}
