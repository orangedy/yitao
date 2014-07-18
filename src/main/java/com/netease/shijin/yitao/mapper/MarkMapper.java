package com.netease.shijin.yitao.mapper;

import java.util.List;

import com.netease.shijin.yitao.bean.MarkRecordBean;

public interface MarkMapper {
    
    public List<Integer> isMarked(String userID, String itemID);

    public boolean addMarkRecord(String userID, String itemID);

    public boolean deleteMarkRecord(String userID, String itemID);

    public List<MarkRecordBean> queryMarkRecord(String userID);
}
