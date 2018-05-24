/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

/**
 *
 * @author AVINASH
 */
import criotam.graph.Exp1Graph;
import criotam.database.Sensordb;
import static criotam.TestServer.builder;
import static criotam.TestServer.fileName;
import static criotam.TestServer.playerID;
import static criotam.TestServer.threadFlag;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

// Server class
public class TestServer 
{
    //public static SensorDatabase database;
    
    public static ServerSocket ss;

    public static volatile boolean flag = true;
    
    public static volatile boolean threadFlag = true;
    
    public static StringBuilder builder;
    
    public static String fileName = "C://Users/AVINASH/Desktop/criotam/csv/";
    
    public static String playerID = "";
    
    public static String tableName = "Exp1_";
    
    public static Socket prevConnectedSocket;
    
    public static int count = 0;

    
    public static void main(String[] args) throws IOException 
    {
        
        flag = true;
        
        playerID = args[0];
        
        tableName += playerID;
        
        count = 0;
        
        System.out.println("STARTING SERVER");

        // server is listening on port 5056
        ss = new ServerSocket(5056);
         
        // running infinite loop for getting
        // client request
        while (flag) 
        {
            Socket s = null;
             
            try
            {
                // socket object to receive incoming client requests
                /*if(count == 0)*/{                   //comment out for single user connection
                    
                s = ss.accept();
                
                count++;
                    
                prevConnectedSocket = s;
                
                System.out.println("A new client is connected : " + s);
                 
                //database = new SensorDatabase();
                
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                 
                System.out.println("Assigning new thread for this client");
 
                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);
 
                // Invoking the start() method
                t.start();
                
                }
                 
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
                count = 0;
            }
        }
    }
    
    public int index = 1 ;
    
    public void exitProgram() {
        
        Sensordb exp1Sensordb = new Sensordb(tableName);
        
        try {
            
            if(builder!=null){
                
                File file ;
      
                index = 1;
                        
                while(true){
                    fileName += "Exp1_"+playerID+"_trial"+index+".csv";
                    file = new File(fileName);
                    if(file.exists() && !file.isDirectory()) {
                        fileName = "C://Users/AVINASH/Desktop/criotam/csv/";
                    }else{
                        System.out.println(fileName+"");
                        PrintWriter pw = new PrintWriter(file);
                        pw.write(builder.toString());
                        pw.close();
                        
                        exp1Sensordb.insertData(tableName, fileName);
                        
                        exp1Sensordb.closeConn();
                        //TODO: store filename
                        break;
                    }
                    index++;
                }
                
            }
            //TODO: store file name in database
            
            System.out.println("CLosing server.......");
            prevConnectedSocket.close();
            ss.close();
            threadFlag = false;
            flag = false;
            count = 0;
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
}
 
// ClientHandler class
class ClientHandler extends Thread 
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    
    public String tableName = "Exp1_";
    
    public StringBuilder builder;
    
    public String fileName = "C://Users/AVINASH/Desktop/criotam/csv/";
    
    public int index = 1;
    
    public Sensordb exp1Sensordb;
    
    public Exp1Graph exp1Graph;
    
    public int time = 0;
      
    ArrayList<Double> yAxis;
    
    ArrayList<Integer> xAxis;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws Exception 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.time = 0;
        xAxis = new ArrayList();
        yAxis = new ArrayList();
        
        builder = new StringBuilder(); 
        TestServer.builder = this.builder;
        this.tableName = "Exp1_" + TestServer.playerID;
        exp1Sensordb = new Sensordb(this.tableName);
        TestServer.threadFlag = true;
        this.exp1Graph = new Exp1Graph();
        
    }
 
    @Override
    public void run() 
    {
        String received;
        String toreturn;
        //TODO: stop using flag
        while (TestServer.threadFlag) 
        {
            try {
  
                // receive the answer from client
                received = dis.readUTF();

                System.out.println(received+"");
                 
                if(received.equals("Exit"))
                { 
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    
                    if(builder!=null){
                
                        File file ;
      
                        index = 1;
                        
                        while(true){
                            fileName += "Exp1_"+playerID+"_trial"+index+".csv";
                            file = new File(fileName);
                            if(file.exists() && !file.isDirectory()) {
                                fileName = "C://Users/AVINASH/Desktop/criotam/csv/";
                            }else{
                                System.out.println(fileName+"");
                                PrintWriter pw = new PrintWriter(file);
                                pw.write(builder.toString());
                                pw.close();
                                
                                exp1Sensordb.insertData(tableName, fileName);
                                
                                exp1Sensordb.closeConn();
                                
                                
                                //TODO: store filename
                                break;
                            }
                            index++;
                        }
                    }
                    
                    TestServer.builder = null;
                    TestServer.count = 0;
                    //TestServer.database.closeConn();
                    //TestServer.database = null;
                    break;
                }
                
                
                xAxis.add(time);
                
                yAxis.add(Double.parseDouble(received.split(":")[0]+""));
                
                time++;
                
                this.exp1Graph.plotData(xAxis, yAxis);

                //TODO: plot graph
                builder.append(received+"\n");
                TestServer.builder = this.builder;
                //System.out.println(TestServer.database.InsertData(received+""));
                 
            } catch (IOException e) {
                e.printStackTrace();
                TestServer.count = 0;
                //TestServer.database.closeConn();
                //TestServer.database = null;
            }
        }
         
        try
        {
            // closing resources
            //this.s.close();
            TestServer.count = 0;
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
