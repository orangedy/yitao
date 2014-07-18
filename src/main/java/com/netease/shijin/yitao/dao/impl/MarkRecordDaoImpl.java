package com.netease.shijin.yitao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.shijin.yitao.bean.MarkRecordBean;
import com.netease.shijin.yitao.dao.MarkRecordDao;
import com.netease.shijin.yitao.mapper.MarkMapper;

@Repository
public class MarkRecordDaoImpl implements MarkRecordDao {
    
    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean isMarked(String userID, String itemID) {
        MarkMapper mapper = sqlSession.getMapper(MarkMapper.class);
        return mapper.isMarked(userID, itemID).size() == 0 ? false : true;
    }

    @Override
    public boolean addMarkRecord(String userID, String itemID) {
        MarkMapper mapper = sqlSession.getMapper(MarkMapper.class);
        return mapper.addMarkRecord(userID, itemID);
    }

    @Override
    public boolean deleteMarkRecord(String userID, String itemID) {
        MarkMapper mapper = sqlSession.getMapper(MarkMapper.class);
        return mapper.deleteMarkRecord(userID, itemID);
    }

    @Override
    public List<MarkRecordBean> queryMarkRecord(String userID) {
        MarkMapper mapper = sqlSession.getMapper(MarkMapper.class);
        return mapper.queryMarkRecord(userID);
    }
    
    

}
