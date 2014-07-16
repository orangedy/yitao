package com.netease.shijin.yitao.dao;

public interface UserDao {

    public boolean isUserExist(String accountID);
    
    public void updateUserInfo(String accountID, String nickName, String iconURL);
    
    public void addUser(String accountID, String nickName, String iconURL);
    
    public long getUserID(String accountID);
}
