package com.netease.shijin.yitao.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netease.shijin.yitao.dao.UserDao;
import com.netease.shijin.yitao.mapper.UserMapper;
import com.netease.shijin.yitao.tool.UUIDUtil;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean isUserExist(String accountID) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> result = mapper.isUserExist(accountID);
        return result.size() != 0 ? true : false;
    }

    @Override
    public void updateUserInfo(String accountID, String nickName, String iconURL) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUserInfo(accountID, nickName, iconURL);
    }

    @Override
    public void addUser(String accountID, String nickName, String iconURL) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String userID = UUIDUtil.getUUID();
        mapper.addUser(userID, accountID, nickName, iconURL);
    }

    @Override
    public String getUserID(String accountID) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserID(accountID);
    }

}
