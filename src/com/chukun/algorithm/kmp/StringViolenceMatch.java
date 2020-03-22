package com.chukun.algorithm.kmp;

/**
 * @auther : chukun
 * @description : 字符串的暴力匹配算法
 * @create : 2019/7/27 22:00
 * @copyright www.hualala.com
 */
public class StringViolenceMatch {

    public static void main(String[] args) {

        String source = "ABCDASCADE";
        String dest = "ADE";
        int index = violenceMatch(source, dest);
        System.out.println("index=" + index);
    }

    /**
     * 字符串，暴力匹配算法
     *
     * @param source
     * @param dest
     * @return
     */
    public static int violenceMatch(String source, String dest) {
        char[] sourceChars = source.toCharArray();
        char[] destChars = dest.toCharArray();
        int i = 0;
        int j = 0;
        while (i < sourceChars.length && j < destChars.length) {
            if (sourceChars[i] == destChars[j]) {
                j++;
                i++;
            } else {
                //将i重置为i的下一位
                i = i - (j - 1);
                //将j置为0
                j = 0;
            }
            if (j == destChars.length) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
