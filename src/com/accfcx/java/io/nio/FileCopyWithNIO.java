package com.accfcx.java.io.nio;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author accfcx
 * @desc
 * NIO通道实现文件复制: 演示ByteBuffer的用法
 * 实际文件复制不需要太过缓冲区，直接使用 FileChannel.transferFrom()方法
 */
public class FileCopyWithNIO {
    private static String str = "/Users/accfcx/Project/demo/TestCase";

    public static void main(String[] args) {
        try{
            copy(str + "/t1.json", str+"/t2.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void copy(String srcPath, String desPath) throws IOException {
        File srcFile = new File(srcPath);
        File desFile = new File(desPath);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;


        try{
            if (!desFile.exists()) {
                desFile.createNewFile();
            }

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();


            int len ;
            ByteBuffer bb = ByteBuffer.allocate(1024);
            while ((len = inChannel.read(bb)) != -1) {
                System.out.println("读取字节数:" + len);
                bb.flip();
                int outLen;
                while ((outLen = outChannel.write(bb)) != 0) {
                    System.out.println("写入字节数：" + outLen);
                }
                bb.clear();
            }
            outChannel.force(true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(outChannel);
            IOUtils.close(fos);
            IOUtils.close(inChannel);
            IOUtils.close(fis);
        }
    }
}
