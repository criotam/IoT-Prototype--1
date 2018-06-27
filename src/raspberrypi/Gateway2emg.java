package raspberrypi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/gateway2emg")
public class Gateway2emg {
	

	private String mc_id_emg = "A0:20:A6:1A:DC:80";
	
	private static boolean flag_end = false, flag_start = false, flag_sb = false, flag_emg = false;
	
	private static ArrayList<Session> session_list = new ArrayList();
	
	private static Queue<String> data_buffer = new LinkedList<>();
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());  
        session_list.add(session);
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
        session_list.remove(session);
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        
    	System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        
        
        if(message.equalsIgnoreCase("identifier_exp2emg:mac_id")) {
        	
			if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_emg)) {
				
				System.out.println("EMG");
				flag_emg = true;
			        		
			}else {
				System.out.println("INVALID MAC ID");
				flag_emg = false;
				
				session.close();
			}
			
        }
       
        
    	data_buffer.add(message);
    	sendData(session);
    	
    }
    
    @OnError
    public void onError(Throwable t, Session session) {
        System.out.println("onError::" + t.getMessage());
        
        if(!session.isOpen())
            session_list.remove(session);
    }
    
    
   public static void sendData(Session session) {
    	
    	while(!data_buffer.isEmpty()) {
    		
    		
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
