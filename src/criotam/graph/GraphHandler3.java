/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.graph;

import criotam.websocketclient.DataListnerHandler;

/**
 *
 * @author AVINASH
 */

public class GraphHandler3 {
    
    private DataListnerHandler dataListenerHandler;
    
    private String fileName;
    
    private String playerID;
    
    private String tableName;
    
    private String uri;
    
    private int tab_count;
    
    public GraphHandler3(String fileName, String playerID, 
            String tableName, String uri, int tab_count, String identifier){
        
        this.fileName = fileName;
        
        this.playerID = playerID;
        
        this.tableName = tableName;
        
        this.uri = uri;
        
        this.tab_count = tab_count;
        
        dataListenerHandler = new DataListnerHandler(uri,
                    playerID, tableName, fileName, tab_count, identifier);
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
    
}
