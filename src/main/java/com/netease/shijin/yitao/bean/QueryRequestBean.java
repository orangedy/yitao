package com.netease.shijin.yitao.bean;

import lombok.Data;

@Data
public class QueryRequestBean {

    //用户所在位置的经度
    private double positionX;
    
    //用户所在位置的纬度
    private double positionY;
    
    //用户选择查看商品的范围，用数字表示，
    private int distanceType;
    
    //用户选择的商品分类，用数字表示
    private int categoryType;
    
    //用户选择的新旧程度
    private int degreeType;
    
    //用户选择的排序方式，0代表按距离排序，1代表按发布时间排序
    private int sort;
    
    //当前查看的页码
    private int page;
    
    //每页展示的数量
    private int count;
}
