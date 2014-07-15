package com.netease.shijin.yitao.mapper;

public interface UserMapper {

    public int isUserExist(String accountID);
    
    public int updateUserInfo(String accountID, String nickName, String iconURL);
    
    public int addUser(String accountID, String nickName, String iconURL);
    
    public int getUserID(String accountID);
}
