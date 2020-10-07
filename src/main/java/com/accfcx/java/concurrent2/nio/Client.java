package com.accfcx.java.concurrent2.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.LockSupport;

/**
 * @author accfcx
 * @desc
 * 发起 socket 连接
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8000));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("喜欢你：jyx");
            LockSupport.parkNanos( 6000*1000*1000);
            writer.println("贾玉雪");
            LockSupport.parkNanos( 6000*1000*1000);
            writer.flush();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("echo return from server: " + reader.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }

    public void test() {

    }
}
