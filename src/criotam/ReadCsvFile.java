/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import criotam.graph.GraphPlotterActivity;
import criotam.graph.GraphPlotterUtil;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AVINASH
 */
public class ReadCsvFile {
    
    public ArrayList<String> message;
    
    public String line;
            
    public String blockChaindata = "";

    public ReadCsvFile(String fileName) throws Exception{
        
        message = new ArrayList();
            
        line = "";
        
        try {
            
            Scanner scanner = new Scanner(new File(fileName));
            Scanner dataScanner = null;
            int index = 0;
            int time_index = 0;
            
            while (scanner.hasNextLine()) {
                //System.out.println("reading csv...");
                
                line = scanner.nextLine().toString().trim();
                
                blockChaindata = blockChaindata +line +"$";
                
                message.add(line);
                
                
            }
            
            showPlot(message);
            
                    
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		
    }
    
    public void showPlot(ArrayList<String> message) throws Exception{
        
        GraphPlotterActivity graphPlotActivity = new GraphPlotterActivity(4, message.get(0).split(":")[0]);
        graphPlotActivity.plotHistoryGraph(message);
        
        //storeDataOnBlockChain();
        
    }
    
    Map<String, String> parameters;
    
    public void storeDataOnBlockChain() throws MalformedURLException, IOException /*throws MalformedURLException, IOException*/{
        
        blockChaindata = blockChaindata.substring(0, blockChaindata.length()-2);
        
        System.out.println("File content:"+ blockChaindata);
        
        URL url = new URL("http://192.168.1.14:3000/"
                + "api/org.criotam.prototype.iotTransactions.readexperiment2emgData");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        
        parameters = new HashMap();
        parameters.put("experiment1", "resource:org.criotam.prototype.iotAssets.experiment2emgData#EX02");
        parameters.put("Raw_value", blockChaindata);
        
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
        con.disconnect();

    }
    
}
