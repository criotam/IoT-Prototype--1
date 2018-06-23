/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.websocketclient;

import criotam.websocketclient.DataListener.connectionListener;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author AVINASH
 */
public class DataListnerHandler {
    
    final CountDownLatch messageLatch = new CountDownLatch(1);
    
    public WebSocketContainer container;
    
    private DataListener loadCellListner;
    
    private String fileName;
    
    private String playerID;
    
    private String tableName;
    
    private String url;
    
    private int tab_count;
    
    private String identifier;
    
    JPanel panel;
    
    
    public interface Conn_Listener{
        public void onConnectionClosed(String fileName);
    }
    
    private Conn_Listener conn_manager;
     
    public DataListnerHandler(String url, String playerID, 
            String tableName, String fileName, int tab_count, String identifier,Conn_Listener conn ) {
        
        this.fileName = fileName;
        
        this.playerID = playerID;
        
        this.tableName = tableName;
        
        this.url = url;
        
        this.tab_count = tab_count;
        
        this.identifier = identifier;
        
        this.conn_manager = conn;
        
    }
    
    public void startConnection(){
        
        container = ContainerProvider.getWebSocketContainer();
        loadCellListner = new DataListener(playerID, tableName, fileName,
                tab_count, new LinkedList<>(), identifier, new connectionListener() {
            @Override
            public void onClosed(String fileName) {
                conn_manager.onConnectionClosed(fileName);
            }
        });
        
        try {
            String uri = url;
            System.out.println("Connecting to " + uri);
            container.connectToServer(loadCellListner, URI.create(uri));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void closeConnection() {
        try {
            if(loadCellListner!=null)
            if(loadCellListner.getSession()!=null)
            loadCellListner.getSession().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isClosed(){
        if(loadCellListner==null)return true;
        if(loadCellListner.getSession() == null)return true;
        return !loadCellListner.getSession().isOpen();
    }
    
    public void reOpenPlot(){
        loadCellListner.reOpenGraphPlot();
    }
    
}
