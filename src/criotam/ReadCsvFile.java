/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import criotam.graph.GraphPlotterUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AVINASH
 */
public class ReadCsvFile {
    
    public ArrayList<Double> y_axisExp2EmgSensor;
    
    public ArrayList<Double> x_axis_time;
    
    public String line;
            
    public ReadCsvFile(String fileName) throws Exception{
        
        y_axisExp2EmgSensor = new ArrayList();
            
        x_axis_time = new ArrayList();
        
        line = "";
        
        try {
            
            Scanner scanner = new Scanner(new File(fileName));
            Scanner dataScanner = null;
            int index = 0;
            int time_index = 0;
            
            while (scanner.hasNextLine()) {
                //System.out.println("reading csv...");
                
                line = scanner.nextLine().toString().trim();
                
                if(line.split(":")[0].equalsIgnoreCase("identifier_exp2emg")){
			
                        dataScanner = new Scanner(line);
			dataScanner.useDelimiter(":");
                        
                        while (dataScanner.hasNext()) {
				String data = dataScanner.next();
                                
				if (index == 0){
					
                                }
				else if (index == 1){
				    y_axisExp2EmgSensor.add(Double.parseDouble(data));
                                }
				else if (index == 2){
                                    x_axis_time.add(Double.parseDouble(data));
                                }	
				else
					System.out.println("invalid data:" + data);
				index++;
			}
			index = 0;
            
                        time_index++;
                }
            }
            
            showPlot(x_axis_time, y_axisExp2EmgSensor);
                    
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		
    }
    
    public void showPlot(ArrayList<Double> time, ArrayList<Double> val) throws Exception{
        
        //GraphPlotterUtil exp1 = new GraphPlotterUtil();
        //exp1.plotData(time, val);
        
    }
    
}
