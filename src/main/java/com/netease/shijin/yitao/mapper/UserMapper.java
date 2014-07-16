package com.netease.shijin.yitao.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public Object isUserExist(String accountID);

    public int updateUserInfo(@Param("accountID") String accountID, @Param("nickName") String nickName,
                    @Param("iconURL") String iconURL);

    public int addUser(@Param("accountID") String accountID, @Param("nickName") String nickName,
                    @Param("iconURL") String iconURL);

    public long getUserID(String accountID);
}
