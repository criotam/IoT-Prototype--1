/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raspberrypi.gateway;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author AVINASH
 */
@ServerEndpoint("/gateway3")
public class Gateway3 {

    @OnMessage
    public String onMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText("You are now Connected to IoT Gateway3");
        return null;
    }

    @OnOpen
    public void onOpen() {
    }

    @OnError
    public void onError(Throwable t) {
    }

    @OnClose
    public void onClose() {
    }
    
}
