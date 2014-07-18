package com.netease.shijin.yitao.service;

import java.util.List;
import java.util.Map;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryRequestBean;

public interface ItemService {

    public List<Map<String, Object>> queryItem(QueryRequestBean query);

    public ItemDetailBean getItemDetail(String itemID);

    public boolean addItem(ItemDetailBean itemDetail);

    public List<ItemBean> queryMyItem(String userID, int page, int count);

    public boolean offShelve(String userID, String itemID);

}
