package com.securion.qr.infrastructure.rest;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/api/v1/qr-codes/websocket")
public class QrCodeWebSocketEndpoint {
    @OnOpen
    public void open(Session session) {
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
    }
}
