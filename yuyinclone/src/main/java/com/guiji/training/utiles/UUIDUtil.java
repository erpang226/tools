package com.guiji.training.utiles;

import java.util.UUID;

/**
 * Description:
 *
 * @version V1.0
 * date: 2019/8/22
 * author: song yong chang
 */
public class UUIDUtil {

    private UUIDUtil(){

    }
    public static String get(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
