package io.github.biezhi.java8.lambda;

import org.junit.Test;

/**
 * @author JackChern @create 2022-08-19 17:26
 */
public class prac1 {
    /**
     * 正常代码
     * @param str
     * @return
     */
    public int getString(String str){

        return str.length();
    }

//    getStrLength(String str) -> str.length();

    public static void main( String[] args ) {

    }

    @Test
    public void test1(){
        Runnable r = () -> System.out.println("lambda");
        r.run();
    }

}
