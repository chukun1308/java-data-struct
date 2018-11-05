package com.chukun.datastruct.stack_queue;

import java.util.Stack;

/**
 * 括号匹配
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 */
public class Solution {

    /**
     * 利用栈的数据结构，实现括号匹配
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(' || c=='[' ||c=='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                Character topChar = stack.pop();
                if(c==')' && topChar!='('){
                    return false;
                }
                if(c==']' && topChar!='['){
                    return false;
                }
                if(c=='}' && topChar!='{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s="({[]})";
        Solution solution = new Solution();
        System.out.println(solution.isValid(s));
    }
}
