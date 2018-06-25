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

@ServerEndpoint("/gateway2")
public class Gateway2 {
	
	private String mc_id_ir_end = "68:C6:3A:AC:9E:9E";
	private String mc_id_ir_start = "84:F3:EB:81:4B:3F";
	private String mc_id_sb = "5C:CF:7F:69:1F:75";
	
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
        
        
        if(message.equalsIgnoreCase("identifier_exp2lc:start_race")){
            System.out.println("Command received"+session_list.size());
            for(Session sessio: session_list){
                {
                   System.out.println("Command sent:"+message);
                   //Sony mac c4:3a:be:bb:b5:49
                   sessio.getBasicRemote().sendText("identifier_exp2lc:start_race");
                   //sessio.getBasicRemote().sendText("identifier_exp2emg:start_race");
                }
            }
            
            data_buffer.add("identifier_exp2emg:start_race");
            
        }else if(message.equalsIgnoreCase("identifier_exp2lc:mac_id")) {
        	
        	if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_ir_end)) {
        		
        		System.out.println("End time IR");
        		flag_end = true;
        		
        	}else {
        		System.out.println("INVALID IR2 DEVICE MAC");
        		flag_end = false;
        		
        		session.close();
        	}
        	
        	if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_ir_start)){
        		
        		System.out.println("START time IR");
        		flag_start = true;
        		
        	}else {
        		System.out.println("INVALID IR1 DEVICE MAC");
        		flag_start = false;
        		
        		session.close();
        	}
        	
        	if(message.split(":")[2].trim().equalsIgnoreCase(mc_id_sb)) {
        		
        		System.out.println("STARTING BLOCK");
        		flag_sb = true;
        		
        	}else {
        		System.out.println("INVALID SB MAC");
        		flag_sb = false;
        		
        		session.close();
        	}
        	
        }else if(message.equalsIgnoreCase("identifier_exp2emg:mac_id")) {
        	
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
