package com.accfcx.java.io.nio;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author accfcx
 * @desc
 * SocketChannel客户端
 */
public class SocketClient {
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        while (!socketChannel.finishConnect()) {
            Thread.sleep(1000);
            System.out.println("");
        }

        File file = new File("t1.json");
        if (file.exists()) {
            System.out.println("yes");
        }

        System.out.println("成功连接到 server");

//        ByteBuffer fileName = charset.encode("t1.json");
//        ByteBuffer contentBuf = ByteBuffer.allocate(1000);
//
//        contentBuf.putLong()

    }
}
