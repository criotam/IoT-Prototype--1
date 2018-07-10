package raspberrypi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.websocket.Session;

public class BroadCastListener extends HttpServlet{

	public static String ip = null;
	
	public static Arduino_Listener_Handler gateway1_handler_lc = null;
	
	public static Arduino_Listener_Handler gateway2_handler_lc = null;
	
	public static Arduino_Listener_Handler gateway2_handler_emg = null;
	
	public static Arduino_Listener_Handler gateway3_handler_fp = null;
	
	public static Arduino_Listener_Handler gateway3_handler_emg = null;
	
	public static String status1="disconnected", status2="disconnected", status3="disconnected", status4="disconnected", status5="disconnected"; 
	
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

	         	        //ip = packet.getAddress().getHostName();
	         	        
	         	       ip = msg.split("@")[1];
	         	        
	         	        if(!status1.equalsIgnoreCase("connecting")) {
	         	        	new EstablishWebSocketConnection1().start();
	         	        	status1 = "connecting";
	         	        }
	         	        
	         	        if(!status2.equalsIgnoreCase("connecting")) {
	         	        	new EstablishWebSocketConnection2().start();
	         	        	status2 = "connecting";
	         	        }
	         	       
	         	        if(!status3.equalsIgnoreCase("connecting")) {
	         	        	new EstablishWebSocketConnection3().start();
	         	        	status3 = "connecting";
	         	        }
	         	      
	         	        if(!status4.equalsIgnoreCase("connecting")) {
	         	        	new EstablishWebSocketConnection4().start();
	         	        	status4 = "connecting";
	         	        }
	         	     
	         	        if(!status5.equalsIgnoreCase("connecting")) {
	         	        	new EstablishWebSocketConnection5().start();
	         	        	status5 = "connecting";
	         	        }
	         	        
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
	
	

	class EstablishWebSocketConnection1 extends Thread {
        
        @Override
        public void run(){
        	//gateway1
    		if(gateway1_handler_lc!=null) {
    			if(gateway1_handler_lc.isClosed()) {
    				gateway1_handler_lc = new Arduino_Listener_Handler("ws://"+
    	        			ip.toString().trim()+":8088/WebServer/loadCellStreaming",new Arduino_Listener_Handler.EventListenerHandler() {
		        	            @Override
		        	            public void onOpen(Session session) {
		        	                System.out.println("onOpen:: BroadCastListener g1");
		        	            }

		        	            @Override
		        	            public void onClose(Session session) {
		        	            	status1 = "closed";
		        	            }

		        	            @Override
		        	            public void onMessage(String message, Session session) {
		        	            	
		        	            }

		        	            @Override
		        	            public void onError(Throwable t) {
		        	            	if(gateway1_handler_lc.isClosed())
		        	            		status1 = "closed";
		        	            }
		        	        });
    				gateway1_handler_lc.startConnection();
    				
    			}
    		}else {
    			gateway1_handler_lc = new Arduino_Listener_Handler("ws://"+
            			ip.toString().trim()+":8088/WebServer/loadCellStreaming",new Arduino_Listener_Handler.EventListenerHandler() {
	        	            @Override
	        	            public void onOpen(Session session) {
	        	            	System.out.println("onOpen:: BroadCastListener g1");
	        	            }

	        	            @Override
	        	            public void onClose(Session session) {
	        	            	status1 = "closed";
	        	            }

	        	            @Override
	        	            public void onMessage(String message, Session session) {
	        	            	
	        	            }

	        	            @Override
	        	            public void onError(Throwable t) {
	        	            	
	        	            	if(gateway1_handler_lc.isClosed())
	        	            		status1 = "closed";
	        	            	
	        	            }
	        	        });
    			gateway1_handler_lc.startConnection();
    		}
    		   
        }
    }
	

	class EstablishWebSocketConnection2 extends Thread {
        
        @Override
        public void run(){
        	//gateway2--------------------------------------------------------
    		if(gateway2_handler_lc!=null) {
    			if(gateway2_handler_lc.isClosed()) {
    				gateway2_handler_lc = new Arduino_Listener_Handler("ws://"+
    	        			ip.toString().trim()+":8088/WebServer/exp2loadcelllistener",new Arduino_Listener_Handler.EventListenerHandler() {
		        	            @Override
		        	            public void onOpen(Session session) {
		        	            	System.out.println("onOpen:: BroadCastListener g2");
		        	            }

		        	            @Override
		        	            public void onClose(Session session) {
		        	            	status2 = "closed";
		        	            }

		        	            @Override
		        	            public void onMessage(String message, Session session) {
		        	            	
		        	            }

		        	            @Override
		        	            public void onError(Throwable t) {
		        	            	if(gateway2_handler_lc.isClosed())
		        	            		status2 = "closed";
		        	            }
		        	        });
    				gateway2_handler_lc.startConnection();
    			}
    			
    		}else {
    			gateway2_handler_lc = new Arduino_Listener_Handler("ws://"+
            			ip.toString().trim()+":8088/WebServer/exp2loadcelllistener",new Arduino_Listener_Handler.EventListenerHandler() {
	        	            @Override
	        	            public void onOpen(Session session) {
	        	            	System.out.println("onOpen:: BroadCastListener g2");
	        	            }

	        	            @Override
	        	            public void onClose(Session session) {
	        	            	status2 = "closed";
	        	            }

	        	            @Override
	        	            public void onMessage(String message, Session session) {
	        	            	
	        	            }

	        	            @Override
	        	            public void onError(Throwable t) {
	        	            	
	        	            	if(gateway2_handler_lc.isClosed())
	        	            		status2 = "closed";
	        	            	
	        	            }
	        	        });
    			gateway2_handler_lc.startConnection();
    		} 
        }
    }


	class EstablishWebSocketConnection3 extends Thread {
        
        @Override
        public void run(){
        	//gateway2--------------------------------------------------------
    		if(gateway2_handler_emg!=null) {
    			if(gateway2_handler_emg.isClosed()) {
    				gateway2_handler_emg = new Arduino_Listener_Handler("ws://"+
    	        			ip.toString().trim()+":8088/WebServer/exp2emglistener",new Arduino_Listener_Handler.EventListenerHandler() {
		        	            @Override
		        	            public void onOpen(Session session) {
		        	            	System.out.println("onOpen:: BroadCastListener g2");
		        	            }

		        	            @Override
		        	            public void onClose(Session session) {
		        	            	status3 = "closed";
		        	            }

		        	            @Override
		        	            public void onMessage(String message, Session session) {
		        	            	
		        	            }

		        	            @Override
		        	            public void onError(Throwable t) {
		        	            	if(gateway2_handler_emg.isClosed())
		        	            		status3 = "closed";
		        	            }
		        	        });
    				gateway2_handler_emg.startConnection();
    			}
    		}else {
    			gateway2_handler_emg = new Arduino_Listener_Handler("ws://"+
            			ip.toString().trim()+":8088/WebServer/exp2emglistener",new Arduino_Listener_Handler.EventListenerHandler() {
	        	            @Override
	        	            public void onOpen(Session session) {
	        	            	System.out.println("onOpen:: BroadCastListener g2");
	        	            }

	        	            @Override
	        	            public void onClose(Session session) {
	        	            	status3 = "closed";
	        	            }

	        	            @Override
	        	            public void onMessage(String message, Session session) {
	        	            	
	        	            }

	        	            @Override
	        	            public void onError(Throwable t) {
	        	            	
	        	            	if(gateway2_handler_emg.isClosed())
	        	            		status3 = "closed";
	        	            	
	        	            }
	        	        });
    			gateway2_handler_emg.startConnection();
    		} 
        }
    }


	class EstablishWebSocketConnection4 extends Thread {
    
	    @Override
	    public void run(){
	    	//gateway3------------------------------------------------------------
			if(gateway3_handler_fp!=null) {
				if(gateway3_handler_fp.isClosed()) {
					gateway3_handler_fp = new Arduino_Listener_Handler("ws://"+
		        			ip.toString().trim()+":8088/WebServer/exp3forceplatelistener",new Arduino_Listener_Handler.EventListenerHandler() {
		        	            @Override
		        	            public void onOpen(Session session) {
		        	            	System.out.println("onOpen:: BroadCastListener g3");
		        	            }

		        	            @Override
		        	            public void onClose(Session session) {
		        	            	status4 = "closed";
		        	            }

		        	            @Override
		        	            public void onMessage(String message, Session session) {
		        	            	
		        	            }

		        	            @Override
		        	            public void onError(Throwable t) {
		        	            	if(gateway3_handler_fp.isClosed())
		        	            		status4 = "closed";
		        	            }
		        	        });
					gateway3_handler_fp.startConnection();
				}
			}else {
				gateway3_handler_fp = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8088/WebServer/exp3forceplatelistener",new Arduino_Listener_Handler.EventListenerHandler() {
	        	            @Override
	        	            public void onOpen(Session session) {
	        	            	System.out.println("onOpen:: BroadCastListener g3");
	        	            }

	        	            @Override
	        	            public void onClose(Session session) {
	        	            	status4 = "closed";
	        	            }

	        	            @Override
	        	            public void onMessage(String message, Session session) {
	        	            	
	        	            }

	        	            @Override
	        	            public void onError(Throwable t) {
	        	            	
	        	            	if(gateway3_handler_fp.isClosed())
	        	            		status4 = "closed";
	        	            	
	        	            }
	        	        });
				gateway3_handler_fp.startConnection();
			} 
	    }

	}


	class EstablishWebSocketConnection5 extends Thread {
    
	    @Override
	    public void run(){
	    	//gateway3------------------------------------------------------------
			if(gateway3_handler_emg!=null) {
				if(gateway3_handler_emg.isClosed()) {
					gateway3_handler_emg = new Arduino_Listener_Handler("ws://"+
		        			ip.toString().trim()+":8088/WebServer/exp3emglistener",new Arduino_Listener_Handler.EventListenerHandler() {
		        	            @Override
		        	            public void onOpen(Session session) {
		        	            	System.out.println("onOpen:: BroadCastListener g3");
		        	            }

		        	            @Override
		        	            public void onClose(Session session) {
		        	            	status5 = "closed";
		        	            }

		        	            @Override
		        	            public void onMessage(String message, Session session) {
		        	            	
		        	            }

		        	            @Override
		        	            public void onError(Throwable t) {
		        	            	if(gateway3_handler_emg.isClosed())
		        	            		status5 = "closed";
		        	            }
		        	        });
					gateway3_handler_emg.startConnection();
				}
			}else {
				gateway3_handler_emg = new Arduino_Listener_Handler("ws://"+
	        			ip.toString().trim()+":8088/WebServer/exp3emglistener",new Arduino_Listener_Handler.EventListenerHandler() {
	        	            @Override
	        	            public void onOpen(Session session) {
	        	            	System.out.println("onOpen:: BroadCastListener g3");
	        	            }

	        	            @Override
	        	            public void onClose(Session session) {
	        	            	status5 = "closed";
	        	            }

	        	            @Override
	        	            public void onMessage(String message, Session session) {
	        	            	
	        	            }

	        	            @Override
	        	            public void onError(Throwable t) {
	        	            	
	        	            	if(gateway3_handler_emg.isClosed())
	        	            		status5 = "closed";
	        	            	
	        	            }
	        	        });
				gateway3_handler_emg.startConnection();
			} 
	    }

	}
	
}
