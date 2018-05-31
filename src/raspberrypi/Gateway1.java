package raspberrypi;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/gateway1")
public class Gateway1 {
	
	
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
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        
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
        
        	BroadCastListener.gateway1_handler_lc.sendMessage(message);
        	if(message.contains("STREAMING")) {
        		//add actual code here
        	}
        }
        
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
        
    }

}
