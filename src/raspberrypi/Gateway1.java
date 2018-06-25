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

@ServerEndpoint("/gateway1")
public class Gateway1 {
	
	private String mc_id_sb = "5C:CF:7F:69:1F:75";
	
	private static Queue<String> data_buffer = new LinkedList<>();
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen Gateway1::" + session.getId());      
        try {
            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
        
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
       
       if(message.equalsIgnoreCase("identifier_exp1lc:mac_id")) {
        	
        	if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_sb)) {
        		
        		System.out.println("STARTING BLOCK");
        		
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
    		
    		if(BroadCastListener.gateway1_handler_lc==null) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(BroadCastListener.gateway1_handler_lc.isClosed()) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else {
            
            	//case exp1lc gwh1, exp2lc gwh2, exp2emg gwh3....
            	if(data_buffer.peek().toString().contains("identifier_exp1lc")) {
            		//add actual code here
            		BroadCastListener.gateway1_handler_lc.sendMessage(data_buffer.peek().toString());
            	}
            }
    		
    		data_buffer.remove();
    		
    	}
    }
    
}
