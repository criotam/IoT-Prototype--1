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

@ServerEndpoint("/gateway2")
public class Gateway2 {
	
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
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        
    	data_buffer.add(message);
    	sendData(session);
    	
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
    
    
   public static void sendData(Session session) {
    	
    	while(!data_buffer.isEmpty()) {
    		
    		if(BroadCastListener.gateway2_handler_lc==null) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(BroadCastListener.gateway2_handler_lc.isClosed()) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else {
            
            	//case exp1lc gwh1, exp2lc gwh2, exp2emg gwh3....
            	if(data_buffer.peek().toString().contains("identifier_exp2lc")) {
            		//add actual code here
            		BroadCastListener.gateway2_handler_lc.sendMessage(data_buffer.peek().toString());
            	}
            }
            
            if(BroadCastListener.gateway2_handler_emg==null) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(BroadCastListener.gateway2_handler_emg.isClosed()) {
            	try {
    				session.getBasicRemote().sendText("Unreachable local server-:(");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else {
            
            	//case exp1lc gwh1, exp2lc gwh2, exp2emg gwh3....
            	if(data_buffer.peek().toString().contains("identifier_exp2emg")) {
            		//add actual code here
            		BroadCastListener.gateway2_handler_emg.sendMessage(data_buffer.peek().toString());
            	}
            }
        
    		data_buffer.remove();
    		
    	}
    }


}
