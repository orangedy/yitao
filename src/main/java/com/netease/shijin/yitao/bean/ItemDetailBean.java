package com.netease.shijin.yitao.bean;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ItemDetailBean extends ItemBean{

    private String itemAddress;
    
    private String sellerID;
    
    private String sellerName;
    
    private String sellerTel;
    
    private Timestamp expiredTime;
    
//    private int itemState;
}
