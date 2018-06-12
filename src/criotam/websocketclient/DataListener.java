/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.websocketclient;

import criotam.graph.GraphPlotterUtil;
import criotam.database.Sensordb;
import static criotam.TestServer.playerID;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.PathParam;

/**
 *
 * @author AVINASH
 */

@ClientEndpoint
public class DataListener {
    
    public String identifier;
    
    private Session currentSession = null;
   
    public StringBuilder builder;
    
    public GraphPlotterUtil graphPlotUtil;
    
    public Sensordb exp1Sensordb;
    
    private String fileName ;
    
    private String tableName = "";
    
    private String playerID = "";
    
    private int tab_count;
    
    private Queue<String> data_buffer;
    
    private boolean flag = true;
    
    
    public DataListener(String playerID, String tableName, 
            String fileName, int tab_count, Queue<String> data_buffer, String identifier){
       
        builder = new StringBuilder();
        
        this.tab_count = tab_count;
        
        try {
            graphPlotUtil = new GraphPlotterUtil(tab_count, identifier);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        this.tableName = tableName;
        
        this.playerID = playerID;
        
        this.fileName = fileName;
        
        exp1Sensordb = new Sensordb(this.tableName);
        
        this.data_buffer = data_buffer;
        
        this.flag = true;
        
    }
    
    public DataListener(){
        
    }
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            currentSession = session;
            String msg = "storeMySession";
            System.out.println("Sending message to endpoint: " );
            session.getBasicRemote().sendText(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void processMessage(String message) {
        
        //System.out.println("Received message in client: " + message);
  
        flag =true;
        
        if(message.contains("identifier"))
            data_buffer.add(message);
        
        plotData();
        
        //System.out.println("queue size:"+ data_buffer.size());
        
    }

    @OnError
    public void processError(Throwable t) throws FileNotFoundException {
        t.printStackTrace();
        if(!this.currentSession.isOpen()){
        if(builder!=null)
            if(builder.length()!=0){
                while(!data_buffer.isEmpty()){
                    //wait
                }
                flag = false;
                saveinFile();
            }
        }
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("token") String token) throws FileNotFoundException{
        System.out.println("Connection Closed ");
        if(builder!=null)
            if(builder.length()!=0){
                while(!data_buffer.isEmpty()){
                    //wait
                }
                flag = false;
                saveinFile();
            }
                
    }
    
    public Session getSession(){
        return this.currentSession;
    }
   
    private int index = 1;
    
    public void saveinFile() {
        System.out.println("Saving file......");
                    
                    if(builder!=null){
                
                        File file ;
      
                        index = 1;
                        
                        Date date = new Date();
                            String DATE = new SimpleDateFormat("dd-MM-yyyy")
                                    .format(date);
                            
                            String TIME = new SimpleDateFormat("hh mm ss aa")
                                    .format(date);
                                    
                            fileName += DATE+"/";
                            
                            file = new File(fileName);
                            //new Timestamp(date.getTime()) //convert to epoch format
                            
                            if(!file.exists()){
                                file.mkdirs();
                            }
                        
                            fileName += playerID+"("+ TIME +")"+".csv";
                            
                            file = new File(fileName);
                            
                                System.out.println(fileName+"");
                                PrintWriter pw;
                                try {
                                   
                                    pw = new PrintWriter(file);
                                
                                    pw.write(builder.toString());
                               
                                    pw.close();
                                
                                 
                                exp1Sensordb.insertData(tableName, fileName);
                                
                                exp1Sensordb.closeConn();
                                
                                } catch (FileNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                        
                    }
                    builder = null;
    }
    
    public void plotData(){
        
        while(!data_buffer.isEmpty()){
                    
                    //System.out.println("Received message in client: " + data_buffer.peek());
           
                    builder.append(data_buffer.peek()+"\n");
                   
                    graphPlotUtil.plotGraph(data_buffer.peek().toString());
                    
                    data_buffer.remove();
            
                }
        
    }
    
}
