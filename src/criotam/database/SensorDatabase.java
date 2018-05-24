/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.database;

/**
 *
 * @author AVINASH
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class SensorDatabase{

    String url = "jdbc:mysql://localhost/sensordb";
    String USER = "root";
    String PASS = "";

   public Connection con;

    public SensorDatabase(){

		try{
		        	
		        createDatabase();
		        	
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection(url, USER, PASS);
		                        
		            String table = "CREATE TABLE IF NOT EXISTS accelerometer (\n"
		                    + "	x_val text NOT NULL,\n"
		                    + "	y_val text NOT NULL,\n"
		                    + "	z_val text NOT NULL\n"
		                    + ");";
		            
		            Statement stmt = con.createStatement();
		            stmt.execute(table);
		            
		            
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
     
    }

     public void createDatabase() {
    	
    	try{

    	    System.out.println("Creating database"); 

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASS);
                        
            Statement ps = con.createStatement();
            
            String sql = "CREATE DATABASE IF NOT EXISTS sensordb";
            
            ps.executeUpdate(sql);
            
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    	
    }

    public String InsertData(String sensorValue){

    	int x = 0;

		try{
			PreparedStatement ps = con.prepareStatement("insert into accelerometer(x_val, y_val, z_val) values(?,?,?)");
			ps.setString(1, sensorValue.split(":")[0]);
			ps.setString(2, sensorValue.split(":")[1]);
			ps.setString(3, sensorValue.split(":")[2]);
					            
			x = ps.executeUpdate();

			if(x==1){
                return "true";
            }

		 }
		 catch(Exception e){
	           e.printStackTrace();
	    }

	    return "false";
				            
    }
    
    public void closeConn(){

    	try{
    		con.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}