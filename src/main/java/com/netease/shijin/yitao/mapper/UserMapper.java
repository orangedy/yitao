package com.netease.shijin.yitao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public List<Integer> isUserExist(String accountID);

    public int updateUserInfo(@Param("accountID") String accountID, @Param("nickName") String nickName,
                    @Param("iconURL") String iconURL);

    public int addUser(@Param("userID") String userID, @Param("accountID") String accountID, @Param("nickName") String nickName,
                    @Param("iconURL") String iconURL);

    public String getUserID(String accountID);
}
