package com.accfcx.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author accfcx
 * @desc
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        socketChannel.configureBlocking(false);

        while (!socketChannel.finishConnect()) {
            System.out.println("正在建立连接");
        }
        ByteBuffer bb = ByteBuffer.allocate(1024);
        for (int i = 0; i< 10; i++) {
            Thread.sleep(1000);
            bb.put(("hello world ! " + i).getBytes());
            bb.flip();
            socketChannel.write(bb);
            bb.clear();
        }
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
