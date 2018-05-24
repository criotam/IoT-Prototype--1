/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.graph;

import criotam.websocketclient.DataListnerHandler_test;

/**
 *
 * @author AVINASH
 */

public class GraphHandler4 {
    
    private DataListnerHandler_test dataListenerHandler;
    
    private String fileName;
    
    private String playerID;
    
    private String tableName;
    
    private String uri;
    
    
    public GraphHandler4(String fileName, String playerID, String tableName, String uri){
        
        this.fileName = fileName;
        
        this.playerID = playerID;
        
        this.tableName = tableName;
        
        this.uri = uri;
        
        dataListenerHandler = new DataListnerHandler_test(uri,
                    playerID, tableName, fileName);
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
