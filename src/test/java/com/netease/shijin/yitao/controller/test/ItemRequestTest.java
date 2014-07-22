package com.netease.shijin.yitao.controller.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.netease.shijin.yitao.bean.BaseRequestBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;

public class ItemRequestTest extends HttpRequestSender{
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XWWW = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public void queryForItem() {
        String url = "http://localhost:8080/item/itemlist";
        QueryRequestBean query = new QueryRequestBean();
        query.setCategoryType(0);
        query.setCount(10);
        query.setPage(1);
        query.setDegreeType(0);
        query.setDistanceType(4);
        query.setPositionX(120);
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
        String url = "http://localhost:8080/item/additem";
        ItemDetailBean item = new ItemDetailBean();
        item.setItemName("hhh");
        item.setItemDescription("hhhhhhhhhhh");
        item.setItemPrice(100);
        item.setItemCategory(1);
        item.setItemDegree(8);
        item.setImgURL("xxx,yyy,zzz");
        item.setSellerID("35feae32d80f498899156323231a22b1");
        item.setSellerName("dy");
        item.setSellerTel("135xxxxxxxx");
        item.setPositionX(120);
        item.setPositionY(40);
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
    
    public void fileUploadTest(){
        String url = "";
        
    }
    
    public void searchItem() {
        String url = "http://10.242.65.171:8080/search?keyword=dsad&page=1&count=10";
        getRequest(url);
    }
    
    public static void main(String[] args) {
        ItemRequestTest test = new ItemRequestTest();
//        test.queryForItem();
//        test.getItemDetailTest();
//        test.addItemTest();
//        test.getMyItemTest();
//        test.offShelveTest();
        test.searchItem();
    }

}
