package com.accfcx.jvm.ch2;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true) {
            list.add(new OOMObject());
        }
    }

}
