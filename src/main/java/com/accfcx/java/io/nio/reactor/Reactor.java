package com.accfcx.java.io.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

/**
 * @author accfcx
 * @desc 关闭socketChannel的条件！！！ 单线程轮训IO事件，基于SectionKey的附件处理读取请求的数据
 * todo echo功能未成功实现
 */
public class Reactor implements Runnable {

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor()).start();
    }

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public Reactor() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9090));

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 连接处理器
        selectionKey.attach(new AcceptHandler(serverSocketChannel, selector));
    }

    @Override
    public void run() {
        try{
            // 轮训
            while(!Thread.interrupted()) {
                if (selector.select() > 0) {
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selected.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        Runnable runnable = (Runnable) selectionKey.attachment();
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                    selected.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接处理器-
     * 处理新连接：
     *  建立并注册
     *  创建IOHandler
     */

    static class AcceptHandler implements Runnable {
        ServerSocketChannel serverSocketChannel;
        Selector selector;

        public AcceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) {
            this.serverSocketChannel = serverSocketChannel;
            this.selector = selector;
        }

        @Override
        public void run() {
            // 接受连接
            try{
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new IoHandler(selector, socketChannel);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * IO事件处理器
     */
    static class IoHandler implements Runnable {
        SelectionKey selectionKey;
        SocketChannel socketChannel;
        ByteBuffer bb = ByteBuffer.allocate(1024);
        // 0: 读 1：写
        int state = 0;
        public IoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            selectionKey = socketChannel.register(selector, OP_READ | OP_WRITE);// IOHandler
            selectionKey.attach(this);
//            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {

            try{
                if (state == 0) {
                    int len;
                    while((len = socketChannel.read(bb)) > 0) {
                        System.out.println("read str: " + new String(bb.array(), 0, len));
                    }
                    // 接收到客户端的输入后，输出
                    bb.flip();
//                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    state = 1;
                } else if (state == 1){
                    socketChannel.write(bb);
                    bb.clear();
//                    selectionKey.interestOps(SelectionKey.OP_READ);
                    state = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
