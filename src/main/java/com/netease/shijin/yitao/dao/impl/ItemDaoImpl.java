package com.netease.shijin.yitao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.shijin.yitao.bean.ItemBean;
import com.netease.shijin.yitao.bean.ItemDetailBean;
import com.netease.shijin.yitao.bean.QueryBean;
import com.netease.shijin.yitao.dao.ItemDao;
import com.netease.shijin.yitao.mapper.ItemMapper;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ItemBean> queryItem(QueryBean query) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        List<ItemBean> list = mapper.queryItem(query);
        return list;
    }

    @Override
    public ItemDetailBean getItemDetial(String itemID) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        ItemDetailBean itemDetail = mapper.getItemDetail(itemID);
        return itemDetail;
    }

    @Override
    public boolean addItem(ItemDetailBean itemDetail) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        if (mapper.addItem(itemDetail) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ItemBean> getMyItem(String userID, int start, int count) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        List<ItemBean> result = mapper.getMyItem(userID, start, count);
        return result;
    }

    @Override
    public boolean offShelve(String userID, String itemID) {
        ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
        if (mapper.offShelve(userID, itemID) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
