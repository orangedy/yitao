package com.netease.shijin.yitao.service;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;

public interface ItemService {

    List<ItemBean> queryItem(QueryBean query);

}
