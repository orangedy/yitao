package com.netease.shijin.yitao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.mapper.ItemMapper;

@Repository
public class ItemDaoImpl implements ItemDao{
    
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ItemBean> queryItem(QueryBean query, double longi, double lati) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        List<ItemBean> list = mapper.queryItem(query, longi, lati);
        return list;
    }

}
