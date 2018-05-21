/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criotam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author AVINASH
 */
public class PlayerDatabase {
    
    String url = "jdbc:mysql://localhost/criotamdb";
    String USER = "root";
    String PASS = "";

   public Connection con;
   
    public PlayerDatabase(){
        
            try{

                    createDatabase();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(url, USER, PASS);
                    String table = "CREATE TABLE IF NOT EXISTS player (\n"
                                        + "	playerid text NOT NULL,\n"
                                        + "	playername text NOT NULL,\n"
                                        + "	age text NOT NULL,\n"
                                        + "	weight text NOT NULL,\n"
                                        + "	height text NOT NULL,\n"
                                        + "	sex text NOT NULL\n"
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

    	    System.out.println("Creating criotamdb database"); 

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

    public String InsertData(String playerInfo){

    	int x = 0;

		try{
			PreparedStatement ps = con.prepareStatement("insert into player(playerid, playername, age, weight, height, sex) values(?,?,?,?,?,?)");
			ps.setString(1, playerInfo.split(":")[0]);
			ps.setString(2, playerInfo.split(":")[1]);
			ps.setString(3, playerInfo.split(":")[2]);
                        ps.setString(4, playerInfo.split(":")[3]);
			ps.setString(5, playerInfo.split(":")[4]);
			ps.setString(6, playerInfo.split(":")[5]);
					            
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
