package com.accfcx.java.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author accfcx
 * @desc
 */
public class UDPServer {

    public static void main(String[] args) throws Exception {
        new UDPServer().receive();
    }

    void receive() throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress("127.0.0.1", 9090));

        Selector selector = Selector.open();

        channel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            ByteBuffer bb = ByteBuffer.allocate(1024);

            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    channel.receive(bb);
                    bb.flip();
                    System.out.println(new String(bb.array(), 0, bb.limit()));
                    bb.clear();
                }
                iterator.remove();
            }
        }
        selector.close();
        channel.close();
    }
}
