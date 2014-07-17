package com.netease.shijin.yitao.controller.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.netease.shijin.yitao.bean.QueryRequestBean;

public class ItemRequestTest {

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static Logger log = LogManager.getLogger(ItemRequestTest.class);
    
    private HttpClient httpClient;

    public HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new DefaultHttpClient();
        }
        return httpClient;
    }

    public void queryForItem() throws UnsupportedEncodingException {
        BufferedReader in = null;
        try {
            HttpPost request = new HttpPost("http://localhost:8080/yitao/item/itemlist");
            request.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
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
            String json = mapper.writeValueAsString(query);
            StringEntity entiry = new StringEntity(json);
            entiry.setContentType(APPLICATION_JSON);
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

    public static void main(String[] args) {
        ItemRequestTest test = new ItemRequestTest();
        try {
            test.queryForItem();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
