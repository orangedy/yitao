package com.netease.shijin.yitao.mapper;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;

public interface ItemMapper {

    List<ItemBean> queryItem(QueryBean query);

}
