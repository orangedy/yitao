package com.netease.shijin.yitao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryBean;

public interface ItemMapper {

    List<ItemBean> queryItem(QueryBean query);

    ItemDetailBean getItemDetail(String itemID);

    int addItem(ItemDetailBean itemDetail);

    List<ItemBean> getMyItem(@Param("userID") String userID, @Param("start") int start, @Param("count") int count);

    int offShelve(@Param("userID") String userID, @Param("itemID") String itemID);

}
