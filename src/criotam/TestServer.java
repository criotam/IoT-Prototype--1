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
import static criotam.TestServer.builder;
import static criotam.TestServer.fileName;
import static criotam.TestServer.playerID;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Server class
public class TestServer 
{
    public static SensorDatabase database;
    
    public static ServerSocket ss;

    public static volatile boolean flag = true;
    
    public static StringBuilder builder;
    
    public static String fileName = "C://Users/AVINASH/Desktop/criotam/csv/";
    
    public static String playerID = "";

    
    public static void main(String[] args) throws IOException 
    {
        
        flag = true;
        
        playerID = args[0];
        
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
                s = ss.accept();
                
                System.out.println("A new client is connected : " + s);
                 
                database = new SensorDatabase();
                
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                 
                System.out.println("Assigning new thread for this client");
 
                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);
 
                // Invoking the start() method
                t.start();
                 
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
    
    public int index = 1 ;
    
    public void exitProgram() {
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
                        //TODO: store filename
                        break;
                    }
                    index++;
                }
                
            }
            //TODO: store file name in database
            
            //System.exit(0);
            ss.close();
            flag = false;
            
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
    
    public Exp1Sensordb exp1Sensordb;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        builder = new StringBuilder(); 
        TestServer.builder = this.builder;
        this.tableName = "Exp1_" + TestServer.playerID;
        exp1Sensordb = new Exp1Sensordb(this.tableName);
    }
 
    @Override
    public void run() 
    {
        String received;
        String toreturn;
        while (true) 
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
                                //TODO: store filename
                                break;
                            }
                            index++;
                        }
                    }
                    
                    TestServer.builder = null;
                    TestServer.database.closeConn();
                    TestServer.database = null;
                    break;
                }
                
                //TODO: store in csv file and plot graph
                builder.append(received+"\n");
                TestServer.builder = this.builder;
                //System.out.println(TestServer.database.InsertData(received+""));
                 
            } catch (IOException e) {
                e.printStackTrace();
                TestServer.database.closeConn();
                TestServer.database = null;
            }
        }
         
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
