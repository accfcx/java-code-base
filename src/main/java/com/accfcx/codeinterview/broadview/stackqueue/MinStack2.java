package com.accfcx.codeinterview.broadview.stackqueue;

import java.util.Stack;

/**
 * @author accfcx
 * @desc minStack 和 dataStack数量一致，始终保持最小值在栈顶【重复值】
 */
public class MinStack2 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MinStack2() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public Integer pop() {
        if (dataStack.empty()) {
            throw new RuntimeException("stack is empty error");
        }
        Integer result = dataStack.pop();

        if (result >= minStack.peek()) {
            minStack.pop();
        }
        return result;
    }

    public void push(Integer value) {
        if (dataStack.empty()) {
            minStack.push(value);
        }
        if (value >= minStack.peek()) {
            System.out.println("minStack top: " + minStack.peek());
            minStack.push(minStack.peek());
        } else {
            minStack.push(value);
        }
        dataStack.push(value);
    }

    public Integer min() {
        if (minStack.empty()) {
            throw new RuntimeException("empty is empty error");
        }
        return minStack.peek();
    }
}
