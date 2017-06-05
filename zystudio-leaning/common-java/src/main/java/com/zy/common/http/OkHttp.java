package com.zy.common.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.util.concurrent.ExecutorService;

/**
 * @author by zy.
 */
public class OkHttp {

    public static void main(String argvs[]){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://localhost:1201/mywebsocket").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();

        WebSocket ws = client.newWebSocket(request, listener);

        // Trigger shutdown of the dispatcher's executor so this process can
        // exit cleanly.
        ExecutorService executorService = client.dispatcher().executorService();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wakeup");
        ws.send("close");
        ws.close(1000, "");

        executorService.shutdown();
    }
}
