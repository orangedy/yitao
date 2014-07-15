package com.netease.shijin.yitao.dao;

public interface UserDao {

    public boolean isUserExist(String accountID);
    
    public long updateUserInfo(String accountID, String nickName, String iconURL);
    
    public long addUser(String accountID, String nickName, String iconURL);
    
    public String getUserID(String accountID);
}
