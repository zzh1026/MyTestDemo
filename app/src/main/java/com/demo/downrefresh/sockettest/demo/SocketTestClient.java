package com.demo.downrefresh.sockettest.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 这个类的作用是:
 * <p>
 * Created by zhaozh on 2017/6/23.
 */

// 步骤1：创建客户端 & 服务器的连接
public class SocketTestClient {
    private boolean isConnected;
    private Socket socket;
    private OutputStream os;
    private BufferedReader br;


    public SocketTestClient() throws IOException {
        initSocket();
    }

    private void initSocket() throws IOException {
        // 创建Socket对象 & 指定服务端的IP及端口号
        socket = new Socket("192.168.1.200", 8788);
        // 判断客户端和服务器是否连接成功
        isConnected = socket.isConnected();

        // 步骤2：客户端 & 服务器 通信
        // 通信包括：客户端 接收服务器的数据 & 发送数据 到 服务器

        //        <-- 操作1：接收服务器的数据 -->
        readInput();

        //        <-- 操作2：发送数据 到 服务器 -->
        writeOutput();

        // 步骤3：断开客户端 & 服务器 连接
        os.close();

        br.close();

        socket.close();

    }

    private void writeOutput() throws IOException {
        // 步骤1：从Socket 获得输出流对象OutputStream
        // 该对象作用：发送数据
        os = socket.getOutputStream();

        // 步骤2：写入需要发送的数据到输出流对象中
        os.write(("Carson_Ho" + "\n").getBytes("utf-8"));
        // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

        // 步骤3：发送数据到服务端
        os.flush();
    }

    private void readInput() throws IOException {
        // 步骤1：创建输入流对象InputStream
        InputStream is = socket.getInputStream();

        // 步骤2：创建输入流读取器对象 并传入输入流对象
        // 该对象作用：获取服务器返回的数据
        InputStreamReader isr = new InputStreamReader(is);
        br = new BufferedReader(isr);

        // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
        br.readLine();
    }


}
