package com.accfcx.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
//        ByteBuffer bb = ByteBuffer.allocate(1024);
//        for (int i = 0; i< 1; i++) {
//            Thread.sleep(1000);
//            String str = "hello world - " + i + "\r\n";
//            System.out.println(str.getBytes().length);
//            bb.put(str.getBytes());
//            bb.flip();
//            socketChannel.write(bb);
//            bb.clear();
//        }
//        socketChannel.shutdownOutput();
//        socketChannel.close();


        Processer processer = new Processer(socketChannel);
        new Thread(processer).start();

    }

    static class Processer implements Runnable {
        final Selector selector;
        final SocketChannel channel;

        Processer(SocketChannel channel) throws IOException {
            //Reactor初始化
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }


        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            Scanner scanner = new Scanner(System.in);
                            System.out.println("请输入发送内容:");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String next = scanner.next();
                                buffer.put((System.currentTimeMillis() + " >>" + next).getBytes());
                                buffer.flip();
                                // 操作三：通过DatagramChannel数据报通道发送数据
                                socketChannel.write(buffer);
                                buffer.clear();
                            }

                        }
                        if (sk.isReadable()) {
                            // 若选择键的IO事件是“可读”事件,读取数据
                            SocketChannel socketChannel = (SocketChannel) sk.channel();

                            //读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                System.out.println("server echo:" + new String(byteBuffer.array(), 0, length));
                                byteBuffer.clear();
                            }

                        }
                        //处理结束了, 这里不能关闭select key，需要重复使用
                        //selectionKey.cancel();
                    }
                    selected.clear();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
