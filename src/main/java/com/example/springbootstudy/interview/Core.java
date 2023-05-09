package com.example.springbootstudy.interview;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/3/1 15:44
 */
public class Core {

    public int longestCommonSubstr(String s1,String s2,int n,int m){
        if (n == 0 || m == 0){
            return 0;
        }
        if(s1.charAt(0) == s2.charAt(0)) {
            return longestCommonSubstr(s1.substring(1), s2.substring(1),n--,m--)+1;
        }else {
            return Math.max(longestCommonSubstr(s1.substring(1),s2,n--,m--), longestCommonSubstr(s1,s2.substring(1),n--,m--));
        }
    }
}
