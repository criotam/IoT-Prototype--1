/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample.dataListener;

import java.io.IOException;
import java.util.ArrayList;
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
@ServerEndpoint("/exp2loadcelllistener")
public class Exp2LoadCellListener {

    private static Map<String, Session> sessions = new ConcurrentHashMap<>();
    
    private static ArrayList<Session> session_list = new ArrayList();
    
    private static Session session;
    
    @OnMessage
    public String onMessage(String message, Session user_session) throws IOException {
        
        /*
        if(message.equalsIgnoreCase("identifier_exp2lc:start_race")){
            System.out.println("Command received"+session_list.size());
            for(Session sessio: session_list){
                if(sessions.get("storeMySession")!=null){
                    System.out.println("command not sent"+message);
                    if(sessions.get("storeMySession")==sessio){
                    }else{
                        System.out.println("command sent"+message);
                        sessio.getBasicRemote().sendText("identifier_exp2lc:start_race");
                    }
                }else{
                   System.out.println("Command sent:"+message);
                   sessio.getBasicRemote().sendText("identifier_exp2lc:start_race");
                }
            }
        }
        */
                //System.out.println("Message from receiver:"+message);
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
        session_list.remove(session);
        sessions.clear();
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.print("#-------------------opened---------------------------");
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        
        session_list.add(session);

        this.session = session;
    }

    @OnError
    public void onError(Throwable t, Session session) {
        System.out.print("error");
        if(!session.isOpen())
        session_list.remove(session);
        sessions.clear();
    }
    
}
