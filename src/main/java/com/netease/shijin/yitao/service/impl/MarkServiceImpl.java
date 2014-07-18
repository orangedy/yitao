package com.netease.shijin.yitao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
