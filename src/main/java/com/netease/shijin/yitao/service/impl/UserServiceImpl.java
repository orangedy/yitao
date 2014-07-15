package com.netease.shijin.yitao.service.impl;

import com.netease.shijin.yitao.dao.UserDao;
import com.netease.shijin.yitao.service.UserService;

public class UserServiceImpl implements UserService {

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
}
