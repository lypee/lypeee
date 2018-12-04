package com.lypee.util;

public class StringTools {
    /**
     */
    public static boolean isEmpty(String string)
    {
        if(string == null || string.equals("") || string.equals("null")){
            return true ;
        }else if("".equals(string.trim())){
            return true ;
        }
        return false ;
    }

    public static boolean isNumber(String string)
    {
        //$匹配字符串结束
        String checkStr = "^[0-9]+$" ;
        if(string == null)
        {
            return false ;
        }
        if(!string.matches(checkStr))
        {
            return false ;
        }
        return true ;
    }
}
