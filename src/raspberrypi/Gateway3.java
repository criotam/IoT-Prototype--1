package raspberrypi;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/gateway3")
public class Gateway3 {
	
	private String mc_id_fp = "5C:CF:7F:69:1F:4B";
	
	private String mc_id_emg = "A0:20:A6:1A:DC:80";

	
	private static Queue<String> data_buffer = new LinkedList<>();
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());        
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        
        if(message.equalsIgnoreCase("identifier_exp3fp:mac_id")) {
        	
        	if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_fp)) {
        		
        		System.out.println("FORCE PLATE");
        		
        	}else {
            	
           	 session.close();
           	
           } 
        	
        }
        
        if(message.equalsIgnoreCase("identifier_exp2emg:mac_id")) {
        	
			if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_emg)) {
				
				System.out.println("EMG");
			        		
			}else {
	        	
	        	session.close();
	        	
	        }
        }

        data_buffer.add(message);
    	sendData(session);
        
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
    
    
    public static void sendData(Session session) {
    	
    	while(!data_buffer.isEmpty()) {
    		
    		if(BroadCastListener.gateway3_handler_fp==null) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(BroadCastListener.gateway3_handler_fp.isClosed()) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else {
            
            	//case exp1lc gwh1, exp2lc gwh2, exp2emg gwh3....
            	if(data_buffer.peek().toString().contains("identifier_exp3fp")) {
            		//add actual code here
            		BroadCastListener.gateway3_handler_fp.sendMessage(data_buffer.peek().toString());
            	}
            }
            
            if(BroadCastListener.gateway3_handler_emg==null) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(BroadCastListener.gateway3_handler_emg.isClosed()) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else {
            
            	//case exp1lc gwh1, exp2lc gwh2, exp2emg gwh3....
            	if(data_buffer.peek().toString().contains("identifier_exp3emg")) {
            		//add actual code here
            		BroadCastListener.gateway3_handler_emg.sendMessage(data_buffer.peek().toString());
            	}
            }
      
    		data_buffer.remove();
    		
    	}
    }


}
