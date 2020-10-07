package com.accfcx.java.concurrent2.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 * NIO - 多线程处理客户端连接请求
 * 服务端接收 socket
 */
public class EchoServer {
    static ExecutorService executorService = Executors.newCachedThreadPool();

    static class HandleMsg implements Runnable {
        // 客户端连接
        Socket socket;

        public HandleMsg(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try{
                //读取socket输入，并返回输入数据
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                os = new PrintWriter(socket.getOutputStream(), true);

                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    System.out.println("server get input from client ：" + inputLine);
                    os.println(inputLine);
                }

                long e = System.currentTimeMillis();
                System.out.println("spend " + (e - b) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try{
            echoServer = new ServerSocket(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(true) {
            try{
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect");
                executorService.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
