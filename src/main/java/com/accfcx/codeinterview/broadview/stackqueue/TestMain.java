package com.accfcx.codeinterview.broadview.stackqueue;

/**
 * @author accfcx
 */
public class TestMain {
    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(3);
//        minStack.push(4);
//        minStack.push(5);
//        minStack.push(2);
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.min());
//        System.out.println(minStack.pop());
//        System.out.println(minStack.min());

//        MinStack2 minStack2 = new MinStack2();
//        minStack2.push(3);
//        minStack2.push(4);
//        minStack2.push(5);
//
//        System.out.println(minStack2.min());
//
//        minStack2.push(2);
//        minStack2.push(1);
//        minStack2.push(0);
//
//        System.out.println(minStack2.min());
//
//        minStack2.pop();
//        System.out.println(minStack2.min());
//        minStack2.pop();
//        System.out.println(minStack2.min());

        TwoStackAndQueue queue = new TwoStackAndQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());


        queue.push(4);
        queue.push(5);
        queue.push(6);

        System.out.println(queue.pop());
        System.out.println(queue);

    }
}
