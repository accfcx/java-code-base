package com.accfcx.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author accfcx
 * @desc
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9090));
        System.out.println("serversocketchannel listen to port: 9090");

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    System.out.println(key + " 新连接开始建立");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    int len;
                    while((len = socketChannel.read(bb)) > 0) {
                        bb.flip();
                        System.out.println("read str: " + new String(bb.array(), 0, len));
                        bb.clear();
                    }
//                    socketChannel.close();
                } else if (key.isConnectable()) {
                    System.out.println(key + " 连接建立完成");
                } else if (key.isWritable()) {
                    System.out.println(key + " write");
                }
            }
            iterator.remove();
        }

        serverSocketChannel.close();
        selector.close();
    }
}
