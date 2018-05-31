package raspberrypi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BroadCastListener extends HttpServlet{

	public static String ip = null;
	
	public static Arduino_Listener_Handler gateway1_handler_lc = null;
	
	public static Arduino_Listener_Handler gateway2_handler_lc = null;
	
	public static Arduino_Listener_Handler gateway2_handler_emg = null;
	
	public static Arduino_Listener_Handler gateway3_handler_fp = null;
	
	public static Arduino_Listener_Handler gateway3_handler_emg = null;
	
	public void init() throws ServletException {
	    try {
	    	 System.out.println("Listening on port 4445");

	    	int port = 4445;

	      // Create a socket to listen on the port.
	      DatagramSocket dsocket = new DatagramSocket(port);

	      // Create a buffer to read datagrams into. If a
	      // packet is larger than this buffer, the
	      // excess will simply be discarded!
	      byte[] buffer = new byte[2048];

	      // Create a packet to receive data into the buffer
	      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

	      // Now loop forever, waiting to receive packets and printing them.
	      
	      Timer t = new Timer();
	         
	         t.scheduleAtFixedRate(new TimerTask() {
	             @Override
	             public void run() {
	                 try {
	                     
	                	 dsocket.receive(packet);

	         	        // Convert the contents to a string, and display them
	         	        String msg = new String(buffer, 0, packet.getLength());
	         	        System.out.println(packet.getAddress().getHostName() + ": "
	         	            + msg);

	         	        ip = packet.getAddress().getHostName();
	         	        
	         	        establishWebSocketConnection();
	         	        
	         	        // Reset the length of the packet before reusing it.
	         	        packet.setLength(buffer.length);
	         	        
	                 } catch (UnknownHostException ex) {
	                     ex.printStackTrace();
	                 } catch (IOException ex) {
	                     ex.printStackTrace();
	                 }
	             }
	         }, 0, 2000);
	         
	    } catch (Exception e) {
	      System.err.println(e);
	    }
	  }
	
	public void establishWebSocketConnection() {
		
		//gateway1
		if(gateway1_handler_lc!=null) {
			if(gateway1_handler_lc.isClosed()) {
				gateway1_handler_lc = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8080/WebServer/loadCellStreaming");
				gateway1_handler_lc.startConnection();
				
			}
		}else {
			gateway1_handler_lc = new Arduino_Listener_Handler("ws://"+
        			ip.toString().trim()+":8080/WebServer/loadCellStreaming");
			gateway1_handler_lc.startConnection();
		}
		
		
		//gateway2--------------------------------------------------------
		if(gateway2_handler_lc!=null) {
			if(gateway2_handler_lc.isClosed()) {
				gateway2_handler_lc = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8080/WebServer/exp2loadcelllistener");
				gateway2_handler_lc.startConnection();
			}
		}else {
			gateway2_handler_lc = new Arduino_Listener_Handler("ws://"+
        			ip.toString().trim()+":8080/WebServer/exp2loadcelllistener");
			gateway2_handler_lc.startConnection();
		}
		
		
		//gateway2--------------------------------------------------------
		if(gateway2_handler_emg!=null) {
			if(gateway2_handler_emg.isClosed()) {
				gateway2_handler_emg = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8080/WebServer/exp2emglistener");
				gateway2_handler_emg.startConnection();
			}
		}else {
			gateway2_handler_emg = new Arduino_Listener_Handler("ws://"+
        			ip.toString().trim()+":8080/WebServer/exp2emglistener");
			gateway2_handler_emg.startConnection();
		}
		
		
		//gateway3------------------------------------------------------------
		if(gateway3_handler_fp!=null) {
			if(gateway3_handler_fp.isClosed()) {
				gateway3_handler_fp = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8080/WebServer/exp3forceplatelistener");
				gateway3_handler_fp.startConnection();
			}
		}else {
			gateway3_handler_fp = new Arduino_Listener_Handler("ws://"+
        			ip.toString().trim()+":8080/WebServer/exp3forceplatelistener");
			gateway3_handler_fp.startConnection();
		}
		
		//gateway3------------------------------------------------------------
		if(gateway3_handler_emg!=null) {
			if(gateway3_handler_emg.isClosed()) {
				gateway3_handler_emg = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8080/WebServer/exp3emglistener");
				gateway3_handler_emg.startConnection();
			}
		}else {
			gateway3_handler_emg = new Arduino_Listener_Handler("ws://"+
        			ip.toString().trim()+":8080/WebServer/exp3emglistener");
			gateway3_handler_emg.startConnection();
		}
	}
	
}
