/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.websocketclient;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author AVINASH
 */
public class DataListnerHandler_test {
    
    final CountDownLatch messageLatch = new CountDownLatch(1);
    
    public WebSocketContainer container;
    
    private LoadCellListener_test loadCellListner;
    
    private String fileName;
    
    private String playerID;
    
    private String tableName;
    
    private String url;
    
    
    public DataListnerHandler_test(String url, String playerID, String tableName, String fileName) {
        
        this.fileName = fileName;
        
        this.playerID = playerID;
        
        this.tableName = tableName;
        
        this.url = url;
        
    }
    
    public void startConnection(){
        
        container = ContainerProvider.getWebSocketContainer();
        loadCellListner = new LoadCellListener_test(playerID, tableName, fileName);
        
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
            loadCellListner.getSession().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isClosed(){
        return loadCellListner.getSession().isOpen();
    }
    
}
