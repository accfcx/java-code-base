package com.accfcx.java.io.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * @author accfcx
 * @desc
 */
public class DatagramChannelClient {

    public void send() throws IOException {
        DatagramChannel channel = DatagramChannel.open();

        channel.configureBlocking(false);

        ByteBuffer bb = ByteBuffer.allocate(1024);

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String next = in.next();
            bb.put((new Date() + " >> " + next).getBytes());
            bb.flip();
            channel.send(bb, new InetSocketAddress("127.0.0.1", 9090));
            bb.clear();
        }
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        new DatagramChannelClient().send();
    }
}
