package com.zy.weibo.controller;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author by zy.
 */
public class EchoWebSocketListener extends WebSocketListener{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Knock, knock!");
        webSocket.send("Hello!");
        webSocket.send(ByteString.decodeHex("deadbeef"));
        //webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("Receiving: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("Receiving: " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("Closing: " + code + " " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }
}
