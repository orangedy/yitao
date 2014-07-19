package com.netease.shijin.yitao.service;

import java.util.List;

import com.netease.shijin.yitao.bean.ItemBean;

public interface MarkService {

    public boolean isMarked(String userID, String itemID);

    public boolean addMark(String userID, String itemID);

    public boolean deleteMarkedItem(String userID, String itemID);

    public List<ItemBean> getMarkList(String userID, int page, int count);
}
