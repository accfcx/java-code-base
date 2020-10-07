package com.accfcx.java.jvm.ch8;

/**
 * @author accfcx
 * @desc 字节码指令的解析器执行过程
 */
public class InstructionExecutionAction {
    public int calc() {
        int a = 100;
        int b = 200;
        int c = 300;
        return (a + b) * c;
    }
}
/**
 * 1 数据先往操作数栈顶推送-入栈
 * 2 把操作数栈顶的操作数存放到局部变量槽中-出栈
 * 3 实际使用到局部变量时，把变量槽中的数据复制到操作数栈顶
 */
