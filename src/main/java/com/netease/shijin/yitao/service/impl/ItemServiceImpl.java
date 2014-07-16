package com.netease.shijin.yitao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.tool.DistanceUtil;

public class ItemServiceImpl implements ItemService{
    
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ItemBean> queryItem(QueryBean query) {
        int distance = query.getDistance();
        double longi = DistanceUtil.getLongi(query.getPositionX(), query.getPostionY(), distance);
        double lati = DistanceUtil.getLati(query.getPositionX(), query.getPostionY(), distance);
        List<ItemBean> list = itemDao.queryItem(query, longi, lati);
        sort(list, query.getSort());
        return list;
    }
    
    public void sort(List<ItemBean> itemList, int sortType){
        
    }

}
