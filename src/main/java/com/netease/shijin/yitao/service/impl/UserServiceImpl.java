package com.netease.shijin.yitao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.shijin.yitao.dao.UserDao;
import com.netease.shijin.yitao.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public long login(String accountID, String nickName, String iconURL) {
        long userID;
        if (userDao.isUserExist(accountID)) {
            userID = userDao.updateUserInfo(accountID, nickName, iconURL);
        } else {
            userID = userDao.addUser(accountID, nickName, iconURL);
        }
        return userID;
    }

    @Override
    public void logout(long userID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void modifyInfo(long userID, String nickName, String address) {
        // TODO Auto-generated method stub

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
