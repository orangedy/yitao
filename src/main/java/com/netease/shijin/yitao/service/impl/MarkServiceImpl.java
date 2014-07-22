package com.netease.shijin.yitao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.dao.MarkRecordDao;
import com.netease.shijin.yitao.service.MarkService;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRecordDao markRecordDao;

    @Override
    public boolean isMarked(String userID, String itemID) {
        boolean isMarked = markRecordDao.isMarked(userID, itemID);
        return isMarked;
    }

    @Override
    public boolean addMark(String userID, String itemID) {
        // TODO validate
        boolean result = markRecordDao.addMarkRecord(userID, itemID);
        
        return result;
    }

    @Override
    public boolean deleteMarkedItem(String userID, String itemID) {
        // TODO validate
        boolean result = markRecordDao.deleteMarkRecord(userID, itemID);
        return result;
    }

    @Override
    public List<ItemBean> getMarkList(String userID, int page, int count) {
        // TODO Auto-generated method stub
        List<ItemBean> result = markRecordDao.queryMarkRecord(userID);
        for(ItemBean item : result) {
            // 图片选择第一张返回
               String imgURL = item.getImgURL();
               imgURL = imgURL.split(",")[0];
               item.setImgURL(imgURL);
           }
        return result;
    }
}
