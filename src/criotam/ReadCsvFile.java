/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import criotam.graph.Exp1Graph;
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
    
    public ArrayList<Double> y_axisSensor1;
    
    public ArrayList<Double> y_axisSensor2;
    
    public ArrayList<Double> y_axisSensor3;
    
    public ArrayList<Integer> x_axis;
            
    public ReadCsvFile(String fileName) throws Exception{
        
        y_axisSensor1 = new ArrayList();
        
        y_axisSensor2 = new ArrayList();
        
        y_axisSensor3 = new ArrayList();
        
        x_axis = new ArrayList();
        
        try {
            
            Scanner scanner = new Scanner(new File(fileName));
            Scanner dataScanner = null;
            int index = 0;
            int time_index = 0;
            
            while (scanner.hasNextLine()) {
                //System.out.println("reading csv...");
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(":");
                        x_axis.add(time_index);
                        
                        while (dataScanner.hasNext()) {
				String data = dataScanner.next();
                                //System.out.println(data+"");
                                
				if (index == 0)
					y_axisSensor1.add(Double.parseDouble(data));
				else if (index == 1)
					y_axisSensor2.add(Double.parseDouble(data));
				else if (index == 2)
					y_axisSensor3.add(Double.parseDouble(data));
				else
					System.out.println("invalid data:" + data);
				index++;
			}
			index = 0;
                        time_index++;
            }
            
            showPlot(x_axis, y_axisSensor1);
                    
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		
    }
    
    public void showPlot(ArrayList<Integer> time, ArrayList<Double> val) throws Exception{
        
        Exp1Graph exp1 = new Exp1Graph();
        exp1.plotData(time, val);
        
    }
    
}
