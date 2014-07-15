package com.netease.shijin.yitao.bean;

import lombok.Data;

@Data
public class ResponseBean {

    private int code;
    
    private String error;
    
    private Object data;
}