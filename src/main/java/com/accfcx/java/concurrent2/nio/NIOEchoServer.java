package com.accfcx.java.concurrent2.nio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 */
public class NIOEchoServer {
    /**
     * 用于处理连接
     */
    private Selector selector;

    /**
     * 客户端请求处理
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();

    static Map<Socket, Long> time_stat = new HashMap<>(10240);


    void startServer() throws  Exception {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

//        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
        InetSocketAddress isa = new InetSocketAddress(8000);
        ssc.socket().bind(isa);
        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e=  0;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();
                if (sk.isAcceptable()) {
//                    doAccept(sk) ;
                } else if (sk.isValid() && sk.isReadable()) {
                    if (!time_stat.containsKey(((SocketChannel)sk.channel()).socket())) {
                        time_stat.put(((SocketChannel)sk.channel()).socket(), System.currentTimeMillis());
                    }
//                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
//                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b= time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:" + (e - b) + " ms");
                }
            }
        }

    }
}
