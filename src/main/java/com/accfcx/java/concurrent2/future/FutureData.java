package com.accfcx.java.concurrent2.future;

/**
 * @author accfcx
 * @desc
 */
public class FutureData implements Data {
    RealData realData = null;
    Boolean ready = false;

    public synchronized void setRealData(RealData realData) {
        if (ready) {
            return;
        }
        this.realData = realData;
        ready = true;

        notifyAll();
    }

    @Override
    public synchronized String getData() {
        while (!ready) {
            try{
                wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.realData.getData();
    }
}
