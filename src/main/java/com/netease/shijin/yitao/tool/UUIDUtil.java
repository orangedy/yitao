package com.netease.shijin.yitao.tool;

import java.util.UUID;

public class UUIDUtil {
    
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");
        return id;
    }

}
