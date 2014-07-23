package com.netease.shijin.yitao.controller.test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.netease.shijin.yitao.bean.BaseRequestBean;

public class MarkRequestTest extends HttpRequestSender{
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XWWW = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    public void addMarkRecordTest() {
        String url = "http://localhost:8080/yitao/mark/addmark";
        BaseRequestBean param = new BaseRequestBean();
        param.setItemID("aaa");
        param.setUserID("e097d3384b95479199e0d81db59b425b");
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
    }
    
    public void deleteMarkRecordTest(){
        String url = "http://localhost:8080/yitao/mark/deletemark";
        BaseRequestBean param = new BaseRequestBean();
        param.setItemID("aaa");
        param.setUserID("e097d3384b95479199e0d81db59b425b");
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
    }
    
    public void getMarkRecordTest(){
        String url = "http://223.252.196.241:8080/item/myitem?userID=7fcee58da6e44de8af1bf99c969493e7&count=15&page=1";
        getRequest(url);
    }
    
    public static void main(String[] args) {
        MarkRequestTest test = new MarkRequestTest();
//        test.addMarkRecordTest();
//        test.deleteMarkRecordTest();
        test.getMarkRecordTest();
    }
    
}
