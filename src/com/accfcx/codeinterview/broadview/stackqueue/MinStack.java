package com.accfcx.codeinterview.broadview.stackqueue;

import java.util.Stack;

/**
 * @author accfcx
 * @desc minStack 和 dataStack 数量不一致
 */
public class MinStack {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public int pop() {
        if(dataStack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        int result = dataStack.pop();
        if (result == minStack.peek()) {
            minStack.pop();
        }
        return result;
    }

    public int top() {
        return dataStack.peek();
    }

    public void push(Integer value) {
        if (minStack.empty()) {
            minStack.push(value);
        } else if (value < minStack.peek()) {
            minStack.push(value);
        }
        dataStack.push(value);
    }

    public Integer min() {
        if (minStack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        return minStack.peek();
    }
}
