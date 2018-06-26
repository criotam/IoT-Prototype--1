/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.dataListener;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
@ServerEndpoint("/exp2emglistener")
public class Exp2EmgListener {

    private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    
    private static Session session;
    
    @OnMessage
    public String onMessage(String message) {
        //System.out.println("Message from receiver:"+message);
        
        //notify at the start of the race
        if(message.contains("start_race")){
            //ring buzzer: send signal to arduino
            //gateway should send the signal to arduino
        }
        
        if(message.toString().trim().equalsIgnoreCase("storeMySession")){
            System.out.println("session stored");
            sessions.put("storeMySession", session);
        }else if(sessions.get("storeMySession")!=null){
            System.out.println("sending to java application");
            try {
                sessions.get("storeMySession").getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @OnClose
    public void onClose() {
        System.out.print("closed");
        sessions.clear();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.print("#-------------------opened---------------------------");
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        
        this.session = session;
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
        System.out.print("error");
        sessions.clear();
    }
   
    
}
