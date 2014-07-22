package com.netease.shijin.yitao.service;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;

public interface SearchService {

    List<ItemBean> search(String keyWord, int page, int count);

}
