package com.accfcx.java.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author accfcx
 * @desc
 * DatagramChannel
 * ServerSocketChannel
 * SocketChannel
 */
public class ChannelTest {
    public static void main(String[] args) throws Exception{
//        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
//        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
//
//        ByteBuffer bb = ByteBuffer.allocateDirect(16 * 1024);


        File tmp = new File("/Users/accfcx/Project/demo/TestCase/test.txt");
        RandomAccessFile file = new RandomAccessFile(tmp, "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);

        WriteData(0, byteBuffer, fileChannel);
        WriteData(1000, byteBuffer, fileChannel);
        WriteData(10000, byteBuffer, fileChannel);

        System.out.println(fileChannel.size());
        fileChannel.close();
        file.close();

    }

    public static void WriteData(int pos, ByteBuffer byteBuffer, FileChannel fileChannel)  throws IOException {
        byteBuffer.clear();

        byteBuffer.put("hello".getBytes());
        byteBuffer.flip();

        fileChannel.position(pos);
        fileChannel.write(byteBuffer);

    }
}
