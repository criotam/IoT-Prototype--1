/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author AVINASH
 */
public class Sensordb {
    
    String url = "jdbc:mysql://localhost/criotamdb";
    String USER = "root";
    String PASS = "";

   public Connection con;
   
   public Sensordb(String tableName){
       
       try{
		        	
		        createDatabase();
		        	
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection(url, USER, PASS);
		                        
		            String table = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
		                    + " id BIGINT NOT NULL AUTO_INCREMENT,\n"
		                    + "	fileName text NOT NULL,\n"
                                    +"primary key (id)"
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
            
            String sql = "CREATE DATABASE IF NOT EXISTS criotamdb";
            
            ps.executeUpdate(sql);
            
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    	
    }
   
   public void insertData(String tableName, String fileName){
       
       int x = 0;

		try{
			PreparedStatement ps = con.prepareStatement("insert into "
                                + tableName +"(fileName) values(?)");
			ps.setString(1, fileName);
					            
			x = ps.executeUpdate();

			if(x==1){
                            System.out.println("Data inserted");
                        }

		 }
		 catch(Exception e){
	           e.printStackTrace();
	    }

 }
   //function to grt raw sensor fileName
   public ResultSet getRawSensorFileNames(String tableName){
       
       try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, USER, PASS);
            
            PreparedStatement ps = con.prepareStatement("select fileName from " + tableName);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return rs;
            }
            
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
       return null;
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
