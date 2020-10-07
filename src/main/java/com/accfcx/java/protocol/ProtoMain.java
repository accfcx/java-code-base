package com.accfcx.java.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author accfcx
 * @desc
 */
public class ProtoMain {
    public static void main(String[] args) throws Exception {
        MsgProtos.Msg msg = buildMsg();

        way1(msg);

        way2(msg);

        way3(msg);
    }

    /**
     * 阻塞二进制码流传输OIO套接字或文件
     * 异步NIO存在粘包/半包问题！！！
     */
    static void way1(MsgProtos.Msg msg) throws Exception {
        byte[] data = msg.toByteArray();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(data);

        byte[] newData = outputStream.toByteArray();
        MsgProtos.Msg newMsg = MsgProtos.Msg.parseFrom(newData);
        System.out.println(newMsg);
    }

    static void way2(MsgProtos.Msg msg) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        msg.writeTo(outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        MsgProtos.Msg newMsg = MsgProtos.Msg.parseFrom(inputStream);
        System.out.println(newMsg);
    }

    /**
     * 字节长度 + 字节数据解决 粘包/半包问题
     * @param msg
     * @throws Exception
     */
    static void way3(MsgProtos.Msg msg) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        msg.writeDelimitedTo(outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        MsgProtos.Msg newMsg = MsgProtos.Msg.parseDelimitedFrom(inputStream);
        System.out.println(newMsg);
    }

    static MsgProtos.Msg buildMsg() {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(10);
        builder.setContent("hello world");

        return builder.build();
    }

}
