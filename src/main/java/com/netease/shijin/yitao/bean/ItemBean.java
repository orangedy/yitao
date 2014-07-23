package com.netease.shijin.yitao.bean;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class ItemBean {
    
    private String itemID;

    private String itemName;
    
    private String itemDescription;
    
    private String imgURL;
    
    private double itemPrice;
    
    private int itemDegree;
    
    private int itemCategory;
    
    private Timestamp publishTime;
    
    private double positionX;
    
    private double positionY;
    
    private int itemState;
}
