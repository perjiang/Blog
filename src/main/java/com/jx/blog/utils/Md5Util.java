package com.jx.blog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {

    public static String hash(String score,String salt,int number){
        Md5Hash hash= new Md5Hash(score, salt, number);
        return hash.toHex();
    }
}
