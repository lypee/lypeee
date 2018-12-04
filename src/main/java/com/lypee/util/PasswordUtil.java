package com.lypee.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;



import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class PasswordUtil {
    /**
     * 生成含有随机盐的密码
     * @param password
     * @return
     */
    public static String geneMD5WithSalt(String password)
    {
        Random random = new Random() ;
        StringBuilder stringBuilder = new StringBuilder(16) ;//16位的salt
        stringBuilder.append(random.nextInt(999999999)).append(random.nextInt(9999999));
        int len = stringBuilder.length() ;
        if(len < 16){
            for(int i = 0 ; i<16-len ; i++)
            {
                stringBuilder.append("0");//末尾添0
            }
        }
        String salt = stringBuilder.toString() ;//getSalt ;
        password = md5Hex(password+salt) ;//32位
        char[] cs = new char[48] ;
        for(int i = 0 ; i < 48 ; i+= 3)
        {
            cs[i] = password.charAt(i/3 *2);
            cs[i+1] = salt.charAt(i/3) ;
            cs[i+2] = password.charAt(i/3 *2 +1 ) ;
        }
        return new String(cs) ;
    }

    /**
     * 检验密码正确性
     * @param password
     * @param md5
     * @return
     */
    public static boolean verifyPassword(String password , String md5)
    {
        char[] cs1 = new char[32] ;
        char[] cs2 = new char[16] ;
        //分离
        for(int i = 0 ; i < 48 ; i++)
        {
            cs1[i/3*2] = md5.charAt(i) ;
            cs1[i/3*2 + 1] = md5.charAt(i+2) ;
            cs2[i/3] = md5.charAt(i+1) ;
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1)) ;
    }
    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String string)
    {
        try
        {
//            如果MD5在debug信息中 返回  .
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes()) ;
            return new String(new Hex().encode(bytes));
        }catch (Exception e)
        {
            e.printStackTrace();
            return null  ;
        }
    }
}
