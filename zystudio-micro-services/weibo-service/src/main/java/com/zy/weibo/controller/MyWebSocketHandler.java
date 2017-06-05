package com.zy.weibo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

/**
 * @author by zy.
 */
public class MyWebSocketHandler implements WebSocketHandler{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("afterConnectionEstablished webSocketSession:{}", webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        logger.info("handleMessage webSocketSession:{} webSocketMessage:{}",
                webSocketSession, webSocketMessage);

        WebSocketMessage hahaMsg = new TextMessage("haha");
        webSocketSession.sendMessage(hahaMsg);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.info("handleTransportError webSocketSession:{} throwable:{}",
                webSocketSession, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("afterConnectionClosed webSocketSession:{} closeStatus:{}",
                webSocketSession, closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }
}