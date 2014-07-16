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
    public String login(String accountID, String nickName, String iconURL) {
        String userID;
        if (userDao.isUserExist(accountID)) {
            userDao.updateUserInfo(accountID, nickName, iconURL);
        } else {
            //TODO add userid
            userDao.addUser(accountID, nickName, iconURL);
        }
        userID = userDao.getUserID(accountID);
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
