package com.netease.shijin.yitao.bean;

import java.util.Date;

import lombok.Data;

@Data
public class UserBean {

    private int userID;
    
    private String nickName;
    
    private String iconURL;
    
    private String accountID;
    
    private String city;
    
    private Date registerTime;
}
