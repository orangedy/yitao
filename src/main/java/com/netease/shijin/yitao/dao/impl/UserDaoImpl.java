package com.netease.shijin.yitao.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.netease.shijin.yitao.dao.UserDao;
import com.netease.shijin.yitao.mapper.UserMapper;

public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean isUserExist(String accountID) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.isUserExist(accountID);
        return result == 1 ? true : false;
    }

    @Override
    public long updateUserInfo(String accountID, String nickName, String iconURL) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUserInfo(accountID, nickName, iconURL);
        long userID = mapper.getUserID(accountID);
        return userID;
    }

    @Override
    public long addUser(String accountID, String nickName, String iconURL) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(accountID, nickName, iconURL);
        long userID = mapper.getUserID(accountID);
        return userID;
    }

    @Override
    public String getUserID(String accountID) {
        // TODO Auto-generated method stub
        return null;
    }

}
