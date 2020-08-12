package com.accfcx.java.concurrent2.future;

/**
 * @author accfcx
 * @desc
 */
public class RealData implements Data {
    private String content;

    public RealData() throws InterruptedException {
        Thread.sleep(1000);
        this.content = "hello";
    }

    @Override
    public String getData() {
        return this.content;
    }
}
