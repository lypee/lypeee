package test;

import org.junit.Test;

public class test {
    @Test
    public void test() throws Exception
    {
//        String string = "" ;
//        System.out.println("".equals(string));
//        System.out.println(string.equals(""));
//        System.out.println(string == "");
//        System.out.println(string == null );
        int[] a  = new int[16] ;
        int[] b = new int[32] ;
       for(int i : a )
       {
           a[i] = i ;
           if(i == 16 )break ;
       }
       for(int i: b)
       {
           b[i] = i ;
           if(i == 32) break ;
       }
    int j = 0 ;
       while(j < 16)
       {
           System.out.print(" ,a : " + a[j]);
       }
       int i = 0 ;
       while(i < 32 )
       {
           System.out.println(" , b: " + b[j]);
       }
    }
    @Test
    public void isEm() throws Exception
    {

    }
}
