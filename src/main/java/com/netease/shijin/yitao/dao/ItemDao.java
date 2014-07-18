package com.netease.shijin.yitao.dao;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryBean;

public interface ItemDao {

    List<ItemBean> queryItem(QueryBean query);

    ItemDetailBean getItemDetial(String itemID);

    boolean addItem(ItemDetailBean itemDetail);

    List<ItemBean> getMyItem(String userID, int page, int count);

    boolean offShelve(String userID, String itemID);

}
