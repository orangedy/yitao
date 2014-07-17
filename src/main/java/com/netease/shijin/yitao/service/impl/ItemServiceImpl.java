package com.netease.shijin.yitao.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.tool.DistanceUtil;
import com.netease.shijin.yitao.tool.PageUtil;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ItemBean> queryItem(QueryRequestBean queryRequest) {
        // 距离处理
        int distanceType = queryRequest.getDistanceType();
        int distance = 0;
        switch (distanceType) {
            case 0:
                distance = 1000;
                break;
            case 1:
                distance = 2000;
                break;
            case 2:
                distance = 5000;
                break;
            case 3:
                distance = 10000;
                break;
            case 4:
                distance = 20000;
                break;
        }
        double longi = DistanceUtil.getLongi(queryRequest.getPositionX(), queryRequest.getPositionY(), distance);
        double lati = DistanceUtil.getLati(queryRequest.getPositionX(), queryRequest.getPositionY(), distance);
        QueryBean query = new QueryBean();
        query.setCategoryType(queryRequest.getCategoryType());
        query.setDegreeType(queryRequest.getDegreeType());
        query.setLongiMin(queryRequest.getPositionX() - longi);
        query.setLongiMax(queryRequest.getPositionX() + longi);
        query.setLatiMin(queryRequest.getPositionY() - lati);
        query.setLatiMax(queryRequest.getPositionY() + lati);
        List<ItemBean> list = itemDao.queryItem(query);
        sort(list, queryRequest);
        list = page(list, queryRequest.getPage(), queryRequest.getCount());
        return list;
    }

    public void sort(List<ItemBean> itemList, final QueryRequestBean queryRequest) {
        if (queryRequest.getSort() == 0) {
            // 按距离排序返回
            Collections.sort(itemList, new Comparator<ItemBean>() {
                public int compare(ItemBean o1, ItemBean o2) {
                    return Double.compare(DistanceUtil.getDistance(o1.getPositionX(), o1.getPositionY(),
                                                                   queryRequest.getPositionX(), queryRequest.getPositionY()),
                                          DistanceUtil.getDistance(o2.getPositionX(), o2.getPositionY(),
                                                                   queryRequest.getPositionX(), queryRequest.getPositionY()));
                }
            });
        } else {
            // 按时间排序返回，默认已经按照时间逆序排序

        }
    }

    public ArrayList<ItemBean> page(List<ItemBean> list, int page, int count) {
        PageUtil<ItemBean> pageUtil = new PageUtil<ItemBean>(list, count);
        return pageUtil.getPagedData(page);
    }

}
