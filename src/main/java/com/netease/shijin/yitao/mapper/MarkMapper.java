package com.netease.shijin.yitao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.MarkRecordBean;

public interface MarkMapper {
    
    public Integer isMarked(@Param("userID")String userID, @Param("itemID")String itemID);

    public boolean addMarkRecord(@Param("userID")String userID, @Param("itemID")String itemID);

    public boolean deleteMarkRecord(@Param("userID")String userID, @Param("itemID")String itemID);

    public List<ItemBean> queryMarkRecord(@Param("userID")String userID);
}
