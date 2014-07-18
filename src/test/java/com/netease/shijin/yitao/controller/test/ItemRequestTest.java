package com.netease.shijin.yitao.controller.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;

public class ItemRequestTest {

    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XWWW = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static Logger log = LogManager.getLogger(ItemRequestTest.class);

    private HttpClient httpClient;

    public HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new DefaultHttpClient();
        }
        return httpClient;
    }

    public void postRequest(String url, String param, String contentType) {
        BufferedReader in = null;
        try {
            HttpPost request = new HttpPost(url);
            request.setHeader(HTTP.CONTENT_TYPE, contentType);

            
            StringEntity entiry = new StringEntity(param);
            entiry.setContentType(contentType);
            request.setEntity(entiry);
            HttpResponse response = getHttpClient().execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String result = sb.toString();
            System.out.println(response.getStatusLine());
            System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getRequest(String url) {
        BufferedReader in = null;
        try {
            HttpGet request = new HttpGet(url);
            HttpResponse response = getHttpClient().execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String result = sb.toString();
            System.out.println(response.getStatusLine());
            System.out.println(result);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void queryForItem() {
        String url = "http://localhost:8080/yitao/item/itemlist";
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
        String url = "http://localhost:8080/yitao/item/additem";
        ItemDetailBean item = new ItemDetailBean();
        item.setItemName("hhh");
        item.setItemDescription("hhhhhhhhhhh");
        item.setItemPrice(100);
        item.setCategory(1);
        item.setDegree(8);
        item.setImgURL("xxx,yyy");
        item.setSellerID("35feae32d80f498899156323231a22b1");
        item.setSellerName("dy");
        item.setSellerTel("135xxxxxxxx");
        item.setPositionX(120);
        item.setPositionY(40);
        item.setAddress("xxx");
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
        String url = "http://localhost:8080/yitao/item/offshelve";
        Map<String, String> param = new HashMap<String, String>();
        param.put("userID", "35feae32d80f498899156323231a22b1");
        param.put("itemID", "1e26bbc6734c40bd9494909b311095da");
//        String str = "{\\"userID\\":\\"35feae32d80f498899156323231a22b1\\",\\"itemID\\":\\"1e26bbc6734c40bd9494909b311095da\\"}";
        String paramStr = "userID=35feae32d80f498899156323231a22b1&itemID=1e26bbc6734c40bd9494909b311095da";
        postRequest(url, paramStr, APPLICATION_XWWW);
    }

    public static void main(String[] args) {
        ItemRequestTest test = new ItemRequestTest();
        test.queryForItem();
//        test.getItemDetailTest();
        test.addItemTest();
//        test.getMyItemTest();
        test.offShelveTest();
    }

}
