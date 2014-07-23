package com.netease.shijin.yitao.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.exception.ParameterException;
import com.netease.shijin.yitao.service.ItemService;
import com.netease.shijin.yitao.tool.DistanceUtil;
import com.netease.shijin.yitao.tool.HttpClientUtil;
import com.netease.shijin.yitao.tool.PageUtil;
import com.netease.shijin.yitao.tool.UUIDUtil;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Map<String, Object>> queryItem(QueryRequestBean queryRequest) throws Exception {
        // 校验
        if (queryRequest.getPositionX() < 0 || queryRequest.getPositionX() > 180 || queryRequest.getPositionY() < 0
                        || queryRequest.getPositionY() > 90) {
            throw new ParameterException();
        }
        if (queryRequest.getPage() <= 0) {
            throw new ParameterException();
        }
        // 距离处理
        int distanceType = queryRequest.getDistanceType();
        int distanceLimit = 5000;
        switch (distanceType) {
            case 0:
                distanceLimit = 5000;
                break;
            case 1:
                distanceLimit = 1000;
                break;
            case 2:
                distanceLimit = 3000;
                break;
            case 3:
                distanceLimit = 10000;
                break;
            case 4:
                distanceLimit = 40000;
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
            // 距离处理
            double distance = DistanceUtil.getDistance(item.getPositionX(), item.getPositionY(), queryRequest.getPositionX(),
                                                       queryRequest.getPositionY());
            // 如果距离小于查询范围，则返回该商品，否则丢弃
            if (distance <= distanceLimit) {
                map.put("item", item);
                map.put("distance", distance);
                // 图片选择第一张返回
                String imgURL = item.getImgURL();
                imgURL = imgURL.split(",")[0];
                item.setImgURL(imgURL);
                // 时间处理
//            item.setPublishTime(item.getPublishTime()/1000);
                result.add(map);
            }
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
        // 验证
        String itemID = UUIDUtil.getUUID();
        itemDetail.setItemID(itemID);
        // 时间格式化，只保存日期，不保存时间
        Timestamp publishTime = itemDetail.getPublishTime();
        Timestamp expiredTime = itemDetail.getExpiredTime();
//        publishTime.setHours(0);
//        publishTime.setMinutes(0);
//        publishTime.setSeconds(0);
        expiredTime.setHours(0);
        expiredTime.setMinutes(0);
        expiredTime.setSeconds(0);
//        图片url处理
//        String domain = imgServer + request.getContextPath() + "/image/";
//        String imgIDStr = itemDetail.getImgURL();
//        String[] imgIDs = imgIDStr.split(",");
//        String imgURLs = "";
//        for (int i = 0; i < imgIDs.length - 1; i++) {
//            imgURLs += (domain + imgIDs[i] + ",");
//        }
//        itemDetail.setImgURL(imgURLs);
        // 地址处理，通过百度地图获取对应经纬度的地址信息
        String itemAddress = getItemAddress(itemDetail.getPositionX(), itemDetail.getPositionY());
        itemDetail.setItemAddress(itemAddress);
        boolean result = itemDao.addItem(itemDetail);
        return result;
    }

    @Override
    public List<ItemBean> queryMyItem(String userID, int page, int count) {
        // TODO 验证
        int start = (page - 1) * count;
        List<ItemBean> list = itemDao.getMyItem(userID, start, count);
        for (ItemBean item : list) {
            // 图片选择第一张返回
            String imgURL = item.getImgURL();
            imgURL = imgURL.split(",")[0];
            item.setImgURL(imgURL);
        }
        return list;
    }

    @Override
    public boolean offShelve(String userID, String itemID) {
        return itemDao.offShelve(userID, itemID);
    }

    public String getItemAddress(double positionX, double positionY) {
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=9db2734176e42df17710beca40cca88c&location=" + positionY + ","
                        + positionX + "&output=json&pois=0";
        String resultJson = HttpClientUtil.sendGetRequest(url);
        ObjectMapper mapper = new ObjectMapper();
        String itemAddress = "";
        try {
            Map map = mapper.readValue(resultJson, Map.class);
            itemAddress = (String) ((Map) map.get("result")).get("formatted_address");
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemAddress;
    }

}
