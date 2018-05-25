/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.websocketclient;

import criotam.graph.Exp1Graph;
import criotam.database.Sensordb;
import static criotam.TestServer.playerID;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class LoadCellListener_test {
    
    
    private Session currentSession = null;
   
    public StringBuilder builder;
    
    public int time = 0;
      
    ArrayList<Double> yAxis;
    
    ArrayList<Integer> xAxis;
    
    public Exp1Graph exp1Graph;
    
    public Sensordb exp1Sensordb;
    
    private String fileName ;
    
    //private String expNo = "";
    
    private String tableName = "";
    
    private String playerID = "";
    
    public LoadCellListener_test(String playerID, String tableName, String fileName){
       
        builder = new StringBuilder();
        
        this.time = 0;
        
        xAxis = new ArrayList();
        
        yAxis = new ArrayList();
        
        try {
            exp1Graph = new Exp1Graph();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        this.tableName = tableName;
        
        this.playerID = playerID;
        
        this.fileName = fileName;
        
        exp1Sensordb = new Sensordb(this.tableName);
    }
    
    public LoadCellListener_test(){
        
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
        System.out.println("Received message in client: " + message);
        //Save it in csv file
        //TODO: processing here based on experiment
        if(message.toString().trim().split(":")[2]!=null&&builder!=null){
            
            xAxis.add(time);
                
            yAxis.add(Double.parseDouble(message.split(":")[0]+""));
                
            time++;
                
            builder.append(message+"\n");
            
            this.exp1Graph.plotData(xAxis, yAxis);
        }
    }

    @OnError
    public void processError(Throwable t) throws FileNotFoundException {
        t.printStackTrace();
        if(builder!=null)
            if(builder.length()!=0)
                saveinFile();
    }
    
    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("token") String token) throws FileNotFoundException{
        System.out.println("Connection Closed ");
        if(builder!=null)
            if(builder.length()!=0)
                saveinFile();
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
                             
                                
                                //TODO: store filename
                                
                        
                    }
                    builder = null;
    }
    
}
