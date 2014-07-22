package com.netease.shijin.yitao.controller.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UserRequestTest extends HttpRequestSender {
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_XWWW = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public void loginTest() {
        String url = "http://10.242.65.171:8080/user/login";
        Map<String, String> param = new HashMap<String, String>();
        param.put("nickName", "dy");
        param.put("accountID", "xxxxxxxxxxxxxxxxxx");
        param.put("iconURL", "http://www.baidu.com");
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
    
    public static void main(String[] args) {
        UserRequestTest test = new UserRequestTest();
        test.loginTest();
    }
}
