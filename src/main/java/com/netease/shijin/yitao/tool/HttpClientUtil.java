package com.netease.shijin.yitao.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

public class HttpClientUtil {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private HttpClientUtil() {
    }

    public static CloseableHttpClient getInstance() {
        return httpClient;
    }

    //简单发get请求的接口
    public static String sendGetRequest(String url) {
        BufferedReader in = null;
        String result = "";
        try {
            HttpGet request = new HttpGet(url);
            HttpResponse response = getInstance().execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
            System.out.println(response.getStatusLine());
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
        return result;
    }
    
    public static String sendPostRequest(String url, String param, String contentType){
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost request = new HttpPost(url);
            request.setHeader(HTTP.CONTENT_TYPE, contentType);
            StringEntity entiry = new StringEntity(param);
            entiry.setContentType(contentType);
            request.setEntity(entiry);
            HttpResponse response = getInstance().execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String NL = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer("");
            sb.append(response.getStatusLine() + NL);
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
            System.out.println(response.getStatusLine());
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
        return result;
    }
}
