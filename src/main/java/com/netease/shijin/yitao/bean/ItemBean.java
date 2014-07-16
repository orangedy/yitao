package com.netease.shijin.yitao.bean;

import lombok.Data;

@Data
public class ItemBean {
    
    private String itemID;

    private String itemName;
    
    private String itemDescription;
    
    private String[] imgURL;
    
    private int itemPrice;
    
    private int condition;
    
    private long timestamp;
    
    private double positionX;
    
    private double positionY;
    
    private int distance;
}
