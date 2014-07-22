package com.netease.shijin.yitao.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.tool.DistanceUtil;
import com.netease.shijin.yitao.tool.PageUtil;
import com.netease.shijin.yitao.tool.UUIDUtil;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Map<String, Object>> queryItem(QueryRequestBean queryRequest) {
        // 距离处理
        int distanceType = queryRequest.getDistanceType();
        int distanceLimit = 0;
        switch (distanceType) {
            case 0:
                distanceLimit = 1000;
                break;
            case 1:
                distanceLimit = 2000;
                break;
            case 2:
                distanceLimit = 5000;
                break;
            case 3:
                distanceLimit = 10000;
                break;
            case 4:
                distanceLimit = 20000;
                break;
        }
        double longi = DistanceUtil.getLongi(queryRequest.getPositionX(), queryRequest.getPositionY(), distanceLimit);
        double lati = DistanceUtil.getLati(queryRequest.getPositionX(), queryRequest.getPositionY(), distanceLimit);
        QueryBean query = new QueryBean();
        query.setCategoryType(queryRequest.getCategoryType());
        query.setDegreeType(queryRequest.getDegreeType());
        query.setLongiMin(queryRequest.getPositionX() - longi);
        query.setLongiMax(queryRequest.getPositionX() + longi);
        query.setLatiMin(queryRequest.getPositionY() - lati);
        query.setLatiMax(queryRequest.getPositionY() + lati);
        List<ItemBean> list = itemDao.queryItem(query);

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(list.size());
        for (ItemBean item : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            //距离处理
            double distance = DistanceUtil.getDistance(item.getPositionX(), item.getPositionY(), queryRequest.getPositionX(),
                                                       queryRequest.getPositionY());
            if (distance <= distanceLimit) {
                map.put("item", item);
                map.put("distance", distance);
            }
            //图片选择第一张返回
            String imgURL = item.getImgURL();
            imgURL = imgURL.split(",")[0];
            item.setImgURL(imgURL);
            //时间处理
//            item.setPublishTime(item.getPublishTime()/1000);
            result.add(map);
        }
        sort(result, queryRequest);
        result = page(result, queryRequest.getPage(), queryRequest.getCount());
        return result;
    }

    @Override
    public ItemDetailBean getItemDetail(String itemID) {
        ItemDetailBean itemDetail = itemDao.getItemDetial(itemID);
        return itemDetail;
    }

    public void sort(List<Map<String, Object>> itemList, final QueryRequestBean queryRequest) {
        if (queryRequest.getSort() == 0) {
            // 按距离排序返回
            Collections.sort(itemList, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Double.compare((double) o1.get("distance"), (double) o2.get("distance"));
                }
            });
        } else {
            // 按时间排序返回，默认已经按照时间逆序排序

        }
    }

    public ArrayList<Map<String, Object>> page(List<Map<String, Object>> list, int page, int count) {
        PageUtil<Map<String, Object>> pageUtil = new PageUtil<Map<String, Object>>(list, count);
        return pageUtil.getPagedData(page);
    }

    @Override
    public boolean addItem(ItemDetailBean itemDetail) {
        //验证
        String itemID = UUIDUtil.getUUID();
        itemDetail.setItemID(itemID);
        //时间格式化，只保存日期，不保存时间
        Timestamp publishTime = itemDetail.getPublishTime();
        Timestamp expiredTime = itemDetail.getExpiredTime();
        publishTime.setHours(0);
        publishTime.setMinutes(0);
        publishTime.setSeconds(0);
        expiredTime.setHours(0);
        expiredTime.setMinutes(0);
        expiredTime.setSeconds(0);
        //图片url存储形式
        boolean result = itemDao.addItem(itemDetail);
        return result;
    }

    @Override
    public List<ItemBean> queryMyItem(String userID, int page, int count) {
        //TODO 验证
        int start = (page - 1)*count;
        List<ItemBean> list = itemDao.getMyItem(userID, start, count);
        return list;
    }

    @Override
    public boolean offShelve(String userID, String itemID) {
        itemDao.offShelve(userID, itemID);
        return false;
    }

}
