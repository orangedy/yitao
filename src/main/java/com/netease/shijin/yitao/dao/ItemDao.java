package com.netease.shijin.yitao.dao;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;

public interface ItemDao {

    List<ItemBean> queryItem(QueryBean query);

}
