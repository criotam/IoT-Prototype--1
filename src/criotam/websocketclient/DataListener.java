/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.websocketclient;

import com.google.auth.oauth2.GoogleCredentials;
import static com.google.cloud.Identity.serviceAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import criotam.ParameterBuilder;
import criotam.graph.GraphPlotterUtil;
import criotam.database.Sensordb;
import static criotam.TestServer.playerID;
import criotam.graph.GraphPlotterActivity;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
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
    
    //public GraphPlotterUtil graphPlotUtil;
    
    public GraphPlotterActivity graphPlotActivity;
    
    public Sensordb exp1Sensordb;
    
    private String fileName ;
    
    private String tableName = "";
    
    private String playerID = "";
    
    private int tab_count;
    
    private Queue<String> data_buffer, msg_buffer;
    
    private boolean flag = true;
    
    private connectionListener conn_manager;
    
    private String blockchaindata;
    
    private static String BLOCK_CHAIN_IP = "192.168.1.14";
    
    public interface connectionListener{
       
        public void onClosed(String fileName);
        
    }
    
    public DataListener(String playerID, String tableName, 
            String fileName, int tab_count, Queue<String> data_buffer, String identifier, connectionListener conn_manager){
       
        builder = new StringBuilder();
        
        this.tab_count = tab_count;
        
        
        try {
            //graphPlotUtil = new GraphPlotterUtil(tab_count, identifier);
            graphPlotActivity = new GraphPlotterActivity(tab_count, identifier);
            //graphPlotActivity.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //graphPlotActivity = new GraphPlotterActivity(tab_count, identifier);
        //graphPlotActivity.setVisible(true);
                
        this.tableName = tableName;
        
        this.playerID = playerID;
        
        this.fileName = fileName;
        
        exp1Sensordb = new Sensordb(this.tableName);
        
        this.data_buffer = data_buffer;
        
        this.msg_buffer = new LinkedList();
        
        this.flag = true;
        
        this.conn_manager = conn_manager;
        
        stream_list = new ArrayList();
        
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("live_streaming");

        //initializeFirebase();
        
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void processMessage(String message) {
        
        //System.out.println("Received message in client: " + message);
  
        flag =true;
        
        if(message.contains("identifier")){
            data_buffer.add(message);
            msg_buffer.add(message);
        }
        
        plotData();
        
        startStreaming();
        
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
                
                //(new storeOnBlockchain()).start();
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
                conn_manager.onClosed(fileName);
                
                //(new storeOnBlockchain()).start();
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
                    
                    blockchaindata = blockchaindata + data_buffer.peek()+"@";
                   
                    //graphPlotUtil.plotGraph(data_buffer.peek().toString());
                    graphPlotActivity.plotGraph(data_buffer.peek().toString());
                    
                    data_buffer.remove();
            
                }
        
    }
    
    public void reOpenGraphPlot(){
        graphPlotActivity.setVisible(true);
    }
    
    
    private class storeOnBlockchain extends Thread{
        
        @Override
        public void run(){
            
            try {
                storeDataOnBlockChain(blockchaindata);
            } catch (IOException ex) {
                Logger.getLogger(DataListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    Map<String, String> parameters;
    
    public void storeDataOnBlockChain( String blockchaindata) throws MalformedURLException, IOException /*throws MalformedURLException, IOException*/{
        
        
        blockchaindata = blockchaindata.substring(0, blockchaindata.length()-2);
        
        System.out.println("File content:"+ blockchaindata);
        
        URL url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment2emgData");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        
        parameters = new HashMap();
        
        
        if(blockchaindata.split("@")[0].toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")){
            
            url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment1lcData");
            
            parameters.put("experiment1", 
                    "resource:org.criotam.prototype.iotAssets.experiment1lcData#EX01");
        
        }else if(blockchaindata.split("@")[0].toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")){
            
            url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment2lcData");
            
            parameters.put("experiment1", 
                    "resource:org.criotam.prototype.iotAssets.experiment2lcData#EX02a");
        
        }else if(blockchaindata.split("@")[0].toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
            
            url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment2emgData");
            
            parameters.put("experiment1", 
                    "resource:org.criotam.prototype.iotAssets.experiment2emgData#EX02b");
        
        }else if(blockchaindata.split("@")[0].toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")){
            
            url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment3lcData");
            
            parameters.put("experiment1", 
                    "resource:org.criotam.prototype.iotAssets.experiment3lcData#EX03a");
        
        }else if(blockchaindata.split("@")[0].toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")){
            
            url = new URL("http://"+ BLOCK_CHAIN_IP+":3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment3emgData");
            
            parameters.put("experiment1", 
                    "resource:org.criotam.prototype.iotAssets.experiment3emgData#EX03b");
        
        }
        
        parameters.put("Raw_value", blockchaindata);
        
        con.setConnectTimeout(100000);
        con.setReadTimeout(100000);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterBuilder.getParamsString(parameters));
        out.flush();
        out.close();
        
        int status = con.getResponseCode();
        if(status == 200){
            System.out.println("#################### add successful----------------------------");
            con.disconnect();
        }else if(status == 500){
            System.out.println("Internal server error");
            con.disconnect();
        }else{
            System.out.println("error");
            con.disconnect();
        }
        //con.disconnect();

    }
    
    
    ArrayList<String> stream_list;
    
    public void startStreaming(){
    
        while(!msg_buffer.isEmpty()){
            
            if(msg_buffer.peek().toString().split(":")[0].equalsIgnoreCase("identifier_exp1lc")){
                
                stream_list.add(msg_buffer.peek().toString());
                
                if(stream_list.size()%20==0){
                  streamData(playerID, "exp1_lc_streaming", stream_list);
                }
                
            }else if(msg_buffer.peek().toString().split(":")[0].equalsIgnoreCase("identifier_exp2lc")){
                
                stream_list.add(msg_buffer.peek().toString());
                
                if(stream_list.size()%20==0)
                    streamData(playerID, "exp2_lc_streaming", stream_list);
                
            }else if(msg_buffer.peek().toString().split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
                
                stream_list.add(msg_buffer.peek().toString());
                
                if(stream_list.size()%20==0)
                    streamData(playerID, "exp2_emg_streaming", stream_list);
                
            }else if(msg_buffer.peek().toString().split(":")[0].equalsIgnoreCase("identifier_exp3fp")){
                
                stream_list.add(msg_buffer.peek().toString());
                
                if(stream_list.size()%20==0)
                    streamData(playerID, "exp3_fp_streaming", stream_list);
                
            }else if(msg_buffer.peek().toString().split(":")[0].equalsIgnoreCase("identifier_exp3emg")){
                
                stream_list.add(msg_buffer.peek().toString());
                
                if(stream_list.size()%20==0)
                    streamData(playerID, "exp3_emg_streaming", stream_list);
                
            }else{
                
            }
            
            msg_buffer.remove();
        }
        
    }
    
    FirebaseDatabase database;
    DatabaseReference ref;
    
    
    
    public void streamData(String player_id, String param, ArrayList<String> data){
        
        ref.child(player_id).child(param).setValue(data, new DatabaseReference.CompletionListener() {
    
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    //System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    //System.out.println("Data saved successfully.");
                }
    }
    });
    }
}
