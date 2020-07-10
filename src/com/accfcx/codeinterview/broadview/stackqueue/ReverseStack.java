package com.accfcx.codeinterview.broadview.stackqueue;

import java.util.Stack;

/**
 * @author accfcx
 * @desc
 */
public class ReverseStack {

    public Integer reverse(Stack<Integer> stack) {
        // 栈顶
        int top =0;
        if (!stack.isEmpty()) {
            top = stack.pop();
            int pre = reverse(stack);

            stack.push(top);
        }
        return top;
    }
}
