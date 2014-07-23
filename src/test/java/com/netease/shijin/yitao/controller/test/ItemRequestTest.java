package com.netease.shijin.yitao.controller.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.netease.shijin.yitao.bean.BaseRequestBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;
import com.netease.shijin.yitao.tool.HttpClientUtil;

public class ItemRequestTest extends HttpRequestSender {
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XWWW = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public void queryForItem() {
//        String url = "http://223.252.196.241:8080/item/itemlist";
        String url = "http://localhost:8080/item/itemlist";
        QueryRequestBean query = new QueryRequestBean();
        query.setCategoryType(0);
        query.setCount(10);
        query.setPage(1);
        query.setDegreeType(0);
        query.setDistanceType(4);
        query.setPositionX(-120);
        query.setPositionY(50);
        query.setSort(0);
        ObjectMapper mapper = new ObjectMapper();
        String param = "";
        try {
            param = mapper.writeValueAsString(query);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        postRequest(url, param, APPLICATION_JSON);
    }

    public void getItemDetailTest() {
        String url = "http://localhost:8080/yitao/item/itemdetail?itemID=34d26d879ff34b05889d93b343f1167f";
        getRequest(url);
    }

    public void addItemTest() {
        String url = "http://223.252.196.241:8080/item/additem";
        ItemDetailBean item = new ItemDetailBean();
        item.setItemName("风味发酵乳");
        item.setItemDescription("好吃的原味风味发酵乳，呵护健康。");
        item.setItemPrice(3);
        item.setItemCategory(1);
        item.setItemDegree(8);
        item.setImgURL("xxx,yyy,zzz");
        item.setSellerID("35feae32d80f498899156323231a22b1");
        item.setSellerName("丁勇");
        item.setSellerTel("135xxxxxxxx");
        item.setPositionX(120);
        item.setPositionY(30);
        item.setItemAddress("xxx");
        item.setPublishTime(new Timestamp(new Date().getTime()));
        item.setExpiredTime(new Timestamp(new Date().getTime() + 7 * 24 * 3600 * 1000));
        ObjectMapper mapper = new ObjectMapper();
        String param = "";
        try {
            param = mapper.writeValueAsString(item);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        postRequest(url, param, APPLICATION_JSON);
    }

    public void getMyItemTest() {
        String url = "http://localhost:8080/yitao/item/myitem?userID=35feae32d80f498899156323231a22b1&page=1&count=10";
        getRequest(url);
    }

    public void offShelveTest() {
        String url = "http://localhost:8080/item/offshelve";
        Map<String, String> param = new HashMap<String, String>();
        param.put("userID", "35feae32d80f498899156323231a22b1");
        param.put("itemID", "1e26bbc6734c40bd9494909b311095da");
        ObjectMapper mapper = new ObjectMapper();
        String paramStr = "";
        try {
            paramStr = mapper.writeValueAsString(param);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        postRequest(url, paramStr, APPLICATION_JSON);
//        String paramStr = "userID=35feae32d80f498899156323231a22b1&itemID=1e26bbc6734c40bd9494909b311095da";
//        postRequest(url, paramStr, APPLICATION_XWWW);
    }

    public void fileUploadTest() {
        String url = "";

    }

    public void searchItem() {
        String url = "http://10.242.65.171:8080/search?keyword=dsad&page=1&count=10";
        getRequest(url);
    }

    public String getItemAddress(double positionX, double positionY) {
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=9db2734176e42df17710beca40cca88c&location=" + positionY + ","
                        + positionX + "&output=json&pois=0";
//        String url = "http://api.map.baidu.com/geocoder?location=" + positionY + "," + positionX
//                        + "&output=json&key=9db2734176e42df17710beca40cca88c";
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
        System.out.println(itemAddress);
        return itemAddress;
    }

    public static void main(String[] args) {
        ItemRequestTest test = new ItemRequestTest();
        test.queryForItem();
//        test.getItemDetailTest();
//        test.addItemTest();
//        test.getMyItemTest();
//        test.offShelveTest();
//        test.searchItem();
//        test.getItemAddress(116.322987, 39.983424);
    }

}
