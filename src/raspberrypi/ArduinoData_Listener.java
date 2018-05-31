package raspberrypi;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ArduinoData_Listener {

	public Session local_Server_session;
	
	public interface EventHandler {
		
		public void onOpen(Session session);
		
		public void onClose(Session session);
		
		public void onMessage(String message, Session session);
		
		public void onError(Throwable t);
		
	}
	
	EventHandler handler;
	
	public ArduinoData_Listener(EventHandler handler) {
		
		this.handler = handler;
	}
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen ::local and gateway server session::" + session.getId()); 
        local_Server_session = session;
        handler.onOpen(session);
    }
	
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
        local_Server_session = null;
        handler.onClose(session);
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        //handler.onMessage(message, session);
    }
    
    @OnError
    public void onError(Throwable t) {
    	local_Server_session = null;
        System.out.println("onError::" + t.getMessage());
        handler.onError(t);
    }
    
    public Session getSession(){
        return this.local_Server_session;
    }
	
}
