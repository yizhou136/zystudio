package com.zy.weibo.bootconfig.feign;

import com.zy.weibo.controller.EchoWebSocketListener;
import feign.Client;
import okhttp3.*;
import okio.BufferedSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author by zy.
 */
public class WebSocketOkHttpClient implements Client {

    private final okhttp3.OkHttpClient delegate;

    public WebSocketOkHttpClient() {
        this(new okhttp3.OkHttpClient());
    }

    public WebSocketOkHttpClient(okhttp3.OkHttpClient delegate) {
        this.delegate = delegate;
    }

    static Request toOkHttpRequest(feign.Request input) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(input.url());

        MediaType mediaType = null;
        boolean hasAcceptHeader = false;
        for (String field : input.headers().keySet()) {
            if (field.equalsIgnoreCase("Accept")) {
                hasAcceptHeader = true;
            }

            for (String value : input.headers().get(field)) {
                if (field.equalsIgnoreCase("Content-Type")) {
                    mediaType = MediaType.parse(value);
                    if (input.charset() != null) {
                        mediaType.charset(input.charset());
                    }
                } else {
                    requestBuilder.addHeader(field, value);
                }
            }
        }
        // Some servers choke on the default accept string.
        if (!hasAcceptHeader) {
            requestBuilder.addHeader("Accept", "*/*");
        }

        byte[] inputBody = input.body();
        boolean isMethodWithBody = "POST".equals(input.method()) || "PUT".equals(input.method());
        if (isMethodWithBody && inputBody == null) {
            // write an empty BODY to conform with okhttp 2.4.0+
            // http://johnfeng.github.io/blog/2015/06/30/okhttp-updates-post-wouldnt-be-allowed-to-have-null-body/
            inputBody = new byte[0];
        }

        RequestBody body = inputBody != null ? RequestBody.create(mediaType, inputBody) : null;
        requestBuilder.method(input.method(), body);
        return requestBuilder.build();
    }

    private static feign.Response toFeignResponse(Response input) throws IOException {
        return feign.Response.builder()
                .status(input.code())
                .reason(input.message())
                .headers(toMap(input.headers()))
                .body(toBody(input.body()))
                .build();
    }

    private static Map<String, Collection<String>> toMap(Headers headers) {
        return (Map) headers.toMultimap();
    }

    private static feign.Response.Body toBody(final ResponseBody input) throws IOException {
        if (input == null || input.contentLength() == 0) {
            return null;
        }
        final Integer length = input.contentLength() >= 0 && input.contentLength() <= Integer.MAX_VALUE ?
                (int) input.contentLength() : null;

        return new feign.Response.Body() {

            @Override
            public void close() throws IOException {
                input.close();
            }

            @Override
            public Integer length() {
                return length;
            }

            @Override
            public boolean isRepeatable() {
                return false;
            }

            @Override
            public InputStream asInputStream() throws IOException {
                return input.byteStream();
            }

            @Override
            public Reader asReader() throws IOException {
                return input.charStream();
            }
        };
    }

    @Override
    public feign.Response execute(feign.Request input, feign.Request.Options options)
            throws IOException {
        okhttp3.OkHttpClient requestScoped;
        if (delegate.connectTimeoutMillis() != options.connectTimeoutMillis()
                || delegate.readTimeoutMillis() != options.readTimeoutMillis()) {
            requestScoped = delegate.newBuilder()
                    .connectTimeout(options.connectTimeoutMillis(), TimeUnit.MILLISECONDS)
                    .readTimeout(options.readTimeoutMillis(), TimeUnit.MILLISECONDS)
                    .build();
        } else {
            requestScoped = delegate;
        }
        Request request = toOkHttpRequest(input);
        Response response = null;
        if (input.url().indexOf("mywebsocket") != -1) {
            //request.newBuilder().url("");
            WebSocket webSocket = requestScoped.newWebSocket(request, new EchoWebSocketListener());
            requestScoped.dispatcher();
            webSocket.send("i am consumer");
            Response.Builder builder = new Response.Builder();
            builder.code(200).body(ResponseBody.create(MediaType.parse("text/html;charset=utf-8"), "OK"));
            response = builder.build();
        }else {
            response = requestScoped.newCall(request).execute();
        }
        return toFeignResponse(response).toBuilder().request(input).build();
    }
}