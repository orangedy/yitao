package com.netease.shijin.yitao.dao;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.MarkRecordBean;

public interface MarkRecordDao {

    public boolean isMarked(String userID, String itemID);

    public boolean addMarkRecord(String userID, String itemID);
    
    public boolean deleteMarkRecord(String userID, String itemID);
    
    public List<ItemBean> queryMarkRecord(String userID);
}
