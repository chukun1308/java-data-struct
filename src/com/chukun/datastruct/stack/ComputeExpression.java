package com.chukun.datastruct.stack;

/**
 * @author chukun
 * 使用stack实现表达式的计算结果
 * 例如: 10+2*6-50
 */
public class ComputeExpression {

    public static void main(String[] args) {
       OperatorExpressionStack numStack = new OperatorExpressionStack(100);
       OperatorExpressionStack expressionStack = new OperatorExpressionStack(100);
       String expression = "20+5*3-8";
       int index=0;
       String keepNum = "";
       while(true) {
           char ch = expression.substring(index, index + 1).charAt(0);
           if (expressionStack.isOperator(ch)) {
               if (!expressionStack.isEmpty()) {
                   //符号栈不为空的情况,判断符号栈，栈顶的元素的优先级是否大于等于将要入栈的元素的优先级
                   if (expressionStack.priority(ch) <= expressionStack.priority(expressionStack.peek())) {
                        //出栈符号栈栈顶元素
                       int oper = expressionStack.pop();
                       //接着弹出数字栈两个元素
                       int nextNum = numStack.pop();
                       int preNum  = numStack.pop();
                       int result  = numStack.calculate(preNum, nextNum, oper);
                       //然后把计算结果压入数字栈，符号压入符号栈
                       numStack.push(result);
                       expressionStack.push(ch);
                   }else{
                       expressionStack.push(ch);
                   }
               } else {
                   //符号栈为空的情况,直接压入符号栈
                   expressionStack.push(ch);
               }
           }else{
               //数字,压入数字栈,char 1 ---> 48  2---->49 3---->50
               //numStack.push(ch-48);
               //数字为多位时，就不能直接入栈了，需要判断
               keepNum+=ch;
               //判断一下是否最后一个字符，如果是，也直接入栈
               if(index==expression.length()-1) {
                   numStack.push(Integer.parseInt(keepNum));
               }else{
                   //向后看一位，后看一位是否是操作符
                   if(expressionStack.isOperator(expression.substring(index+1,index+2).charAt(0))){
                       numStack.push(Integer.parseInt(keepNum));
                       //清空 keepNum
                       keepNum="";
                   }
               }
           }
           index++;
           if(index>=expression.length()){
               //表示表达式已经扫描完毕
               break;
           }
       }
        //符号栈不为空，弹出符号栈，直至为空，计算数字栈的栈
        while(!expressionStack.isEmpty()) {
            int operator = expressionStack.pop();
            int nextNum = numStack.pop();
            int preNum = numStack.pop();
            int result = numStack.calculate(preNum, nextNum, operator);
            numStack.push(result);
        }
        //到这里，数字栈只有一个元素
        System.out.printf("20+5*3-8=%d",numStack.pop());
    }
}

class OperatorExpressionStack extends ArrayStack{

    public OperatorExpressionStack(int maxSize) {
        super(maxSize);
    }

    /**
     * 判断元素的优先级
     * @param ch
     * @return
     */
    public int priority(int ch){
        if(ch == '+' || ch=='-'){
            return 0;
        }
        else if(ch=='*' || ch=='/'){
            return 1;
        }
        return -1;
    }

    /**
     * 计算栈中两个元素的值
     * @param preNum
     * @param nextNum
     * @param oper
     * @return
     */
    public int calculate(int preNum,int nextNum,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = preNum+nextNum;
                break;
            case '-':
                res = preNum-nextNum;
                break;
            case '*':
                res = preNum*nextNum;
                break;
            case '/':
                res = preNum/nextNum;
                break;
        }
        return res;
    }

    public boolean isOperator(int ch){
        return ch=='+'||ch=='-'||ch=='*'||ch=='/';
    }
}
