package com.netease.shijin.yitao.service;

public interface UserService {
    
    public String login(String accountID, String nickName, String iconURL);
    
    public void logout(long userID);

    public void modifyInfo(long userID, String nickName, String address);
}
