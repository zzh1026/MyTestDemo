package com.demo.downrefresh.socket.helper;

import android.support.annotation.NonNull;

import com.demo.downrefresh.socket.SocketClient;


/**
 * SocketClientDelegate
 * Created by vilyever on 2016/5/30.
 * Feature:
 */
public interface SocketClientDelegate {
    void onConnected(SocketClient client);
    void onDisconnected(SocketClient client);
    void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket);

    class SimpleSocketClientDelegate implements SocketClientDelegate {
        @Override
        public void onConnected(SocketClient client) {

        }

        @Override
        public void onDisconnected(SocketClient client) {

        }

        @Override
        public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {

        }
    }
}
