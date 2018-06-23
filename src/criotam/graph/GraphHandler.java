/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.graph;

import criotam.websocketclient.DataListnerHandler;
import criotam.websocketclient.DataListnerHandler.Conn_Listener;

/**
 *
 * @author AVINASH
 */

public class GraphHandler {
    
    private DataListnerHandler dataListenerHandler;
    
    private String fileName;
    
    private String playerID;
    
    private String tableName;
    
    private String uri;
    
    private int tab_count;
    
    public interface Conn_Manager{
        public void onConnClosed(String fileName);
    }
    
    private Conn_Manager conn_manager;
    
    public GraphHandler(String fileName, String playerID, 
            String tableName, String uri, int tab_count, String identifier, Conn_Manager conn_manager){
        
        this.fileName = fileName;
        
        this.playerID = playerID;
        
        this.tableName = tableName;
        
        this.uri = uri;
        
        this.tab_count = tab_count;
        
        this.conn_manager = conn_manager;
        
        dataListenerHandler = new DataListnerHandler(uri,
                    playerID, tableName, fileName, tab_count, identifier, new Conn_Listener() {
            @Override
            public void onConnectionClosed(String fileName) {
                conn_manager.onConnClosed(fileName); 
            }
        });
        
    }
    
    public void start(){
        new WebSocketHandler().start();
    }
    
    class WebSocketHandler extends Thread {
        
        @Override
        public void run(){
                    
            dataListenerHandler.startConnection();
        
        }
    }
    
    public void close(){
        
        dataListenerHandler.closeConnection();
        
    }    
    
    public boolean isClosed(){
        return dataListenerHandler.isClosed();
    }
    
    public void reOpenPlot(){
        dataListenerHandler.reOpenPlot();
    }
    
}
