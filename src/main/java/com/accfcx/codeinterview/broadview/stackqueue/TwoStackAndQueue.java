package com.accfcx.codeinterview.broadview.stackqueue;

import java.util.Stack;

/**
 * @author accfcx
 * @desc two stack to implement a queue
 */
public class TwoStackAndQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(Integer value) {
        // 向第一个栈添加最新元素
        stack1.push(value);
    }

    public Integer pop() {
        // 第2个栈为空时，把stack1元素全部转入stack2中并直接弹出top
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new RuntimeException("queue is empty error");
        }
        return stack2.pop();
    }

    @Override
    public String toString() {
        return "TwoStackAndQueue{" +
                "stack1=" + stack1 +
                ", stack2=" + stack2 +
                '}';
    }
}
