package com.kangyonggan.app.dfjz.web.controller;

import lombok.extern.log4j.Log4j2;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * @author kangyonggan
 * @since 7/14/17
 */
//@ServerEndpoint("chat/server")
@Log4j2
public class WebSocketServer {

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("Received: " + message);

        session.getBasicRemote().sendText("This is the first server message");

        int sentMessages = 0;
        while (sentMessages < 3) {
            Thread.sleep(5000);
            session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
            sentMessages++;
        }

        // Send a final message to the client
        session.getBasicRemote().sendText("This is the last server message");
    }

    @OnOpen
    public void onOpen() {
        log.info("Client connected");
    }

    @OnClose
    public void onClose() {
        log.info("Connection closed");
    }

}
