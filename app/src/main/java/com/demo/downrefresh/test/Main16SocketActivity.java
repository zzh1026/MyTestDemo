package com.demo.downrefresh.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.downrefresh.R;
import com.demo.downrefresh.socket.SocketClient;
import com.demo.downrefresh.socket.helper.SocketClientAddress;
import com.demo.downrefresh.socket.helper.SocketClientDelegate;
import com.demo.downrefresh.socket.helper.SocketClientReceiveDelegate;
import com.demo.downrefresh.socket.helper.SocketResponsePacket;
import com.demo.downrefresh.socket.server.SocketServer;
import com.demo.downrefresh.socket.server.SocketServerClient;
import com.demo.downrefresh.socket.server.SocketServerDelegate;
import com.demo.downrefresh.socket.util.CharsetUtil;
import com.demo.downrefresh.socket.util.IPUtil;

/**
 *
 *  socket
 */
public class Main16SocketActivity extends Activity {
    public String[] src = {
            "{\"parameters\":{\"areaId\":35,\"latitude\":39.917957,\"longitude\":116.453451},\"server\":\"location\",\"userToken\":\"bc329959-194c-439f-93a8-7e8329675ccf\"}"};

    private static final String TAG = "hehe";
    final Main16SocketActivity self = this;

    private SocketServer socketServer;

    //配置服务器端
    protected SocketServer getSocketServer() {
        if (this.socketServer == null) {
            this.socketServer = new SocketServer();

            this.socketServer.setCharsetName(CharsetUtil.UTF_8); // 设置编码为UTF-8
            this.socketServer.getHeartBeatHelper().setHeartBeatInterval(1000 * 10);
            this.socketServer.getHeartBeatHelper().setSendString(null);

            this.socketServer.getSocketPacketHelper().setSendHeaderData(null);
            this.socketServer.getSocketPacketHelper().setSendTrailerData("\r\n".getBytes());

            this.socketServer.getSocketPacketHelper().setReceiveHeaderData(null);
//            this.socketServer.getSocketPacketHelper().setReceiveTrailerData("\r\n".getBytes());
//            this.socketServer.getSocketPacketHelper().setReceiveTrailerString("\r\n");
            this.socketServer.getSocketPacketHelper().setReceiveTrailerString(null);

            this.socketServer.registerSocketServerDelegate(new SocketServerDelegate() {
                @Override
                public void onServerBeginListen(SocketServer socketServer, int port) {
                    Log.i(TAG, "服务端 : socket 开始监听 , 端口为 " + port);
                }

                @Override
                public void onServerStopListen(SocketServer socketServer, int port) {
                    Log.i(TAG, "服务端 : socket 停止监听 , 端口为 " + port);
                }

                @Override
                public void onClientConnected(SocketServer socketServer, SocketServerClient socketServerClient) {
                    Log.i(TAG, "服务端 : socket 连接中");

                    self.setServerListeningSocketServerClient(socketServerClient);
                }

                @Override
                public void onClientDisconnected(SocketServer socketServer, SocketServerClient socketServerClient) {
                    Log.i(TAG, "服务端 : socket 断开连接了");
                    self.setServerListeningSocketServerClient(null);
                }
            });
        }
        return this.socketServer;
    }

    private SocketClient localSocketClient;

    protected SocketClient getLocalSocketClient() {
        if (this.localSocketClient == null) {
            this.localSocketClient = new SocketClient(new SocketClientAddress(IPUtil.getIPAddress(true), getSocketServer().getPort()));

            this.localSocketClient.setCharsetName(CharsetUtil.UTF_8);

            this.localSocketClient.getHeartBeatHelper().setHeartBeatInterval(1000 * 10);
            this.localSocketClient.getHeartBeatHelper().setSendString(null);

            this.localSocketClient.getSocketPacketHelper().setSendHeaderData(null);
//            this.localSocketClient.getSocketPacketHelper().setSendTrailerData(null);
            this.localSocketClient.getSocketPacketHelper().setSendTrailerString("\r\n");

            this.localSocketClient.getSocketPacketHelper().setReceiveHeaderData(null);
            this.localSocketClient.getSocketPacketHelper().setReceiveTrailerData(null);

            this.localSocketClient.getSocketPacketHelper().setSegmentLength(-1);

            this.localSocketClient.registerSocketClientDelegate(new SocketClientDelegate() {
                @Override
                public void onConnected(SocketClient client) {
                    Log.i(TAG, "客户端 : socket 连接成功 ");
                    for (int i = 0; i < 10; i++) {
                        try {
                            client.sendData(src[i % src.length].getBytes("UTF-8"));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i(TAG, "客户端, socket发送数据失败");
                        }
                    }
                }

                @Override
                public void onDisconnected(SocketClient client) {
                    Log.i(TAG, "客户端 : socket 断开连接 ");

                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    client.connect();
                    Log.i(TAG, "客户端 : socket 尝试重新连接!!!! ");
                }

                @Override
                public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
                    Log.i(TAG, "客户端 : socket 获取到了数据!!!! "
                            + "  【" + responsePacket.getMessage() + "】 ");
                }
            });
            this.localSocketClient.registerSocketClientReceiveDelegate(new SocketClientReceiveDelegate() {
                @Override
                public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
                    Log.i(TAG, "客户端 : socket 监听到了获取数据"
                            + "  【" + responsePacket.getMessage() + "】 ");
                }

                @Override
                public void onHeartBeat(SocketClient socketClient) {
                }
            });
        }
        return this.localSocketClient;
    }

    private SocketServerClient serverListeningSocketServerClient;

    protected Main16SocketActivity setServerListeningSocketServerClient(SocketServerClient serverListeningSocketServerClient) {
        this.serverListeningSocketServerClient = serverListeningSocketServerClient;
        if (serverListeningSocketServerClient == null) {
            return this;
        }

        this.serverListeningSocketServerClient.registerSocketClientDelegate(new SocketClientDelegate() {
            @Override
            public void onConnected(SocketClient client) {
                Log.i(TAG, "服务端 : socket 连接成功 ");
            }

            @Override
            public void onDisconnected(SocketClient client) {
                Log.i(TAG, "服务端 : socket 断开连接 ");
            }

            @Override
            public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
                Log.i(TAG, "服务端 : socket 获取到了数据!!!! "
                        + "  " + responsePacket.getMessage() + " ");
            }
        });
        return this;
    }

    protected SocketClient getServerListeningSocketServerClient() {
        return this.serverListeningSocketServerClient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        getSocketServer().beginListenFromPort(80);

        getLocalSocketClient().connect();
        boolean shouldSend = true;

        if (shouldSend) {
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    getLocalSocketClient().sendString("你好");
//                    try {
//                        getLocalSocketClient().sendData("HELLO".getBytes("UTF-8"));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }

//                    getLocalSocketClient().sendString("你也好");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
//                            for (int i = 0; i < 10; i++) {
//                                self.getLocalSocketClient().sendString(src[i % src.length]);
//                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                        }
                    }.execute();
                }
            }, 5 * 1000);
        }
    }
}
