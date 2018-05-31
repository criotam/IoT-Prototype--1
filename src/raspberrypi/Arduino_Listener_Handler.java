package raspberrypi;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import raspberrypi.ArduinoData_Listener.EventHandler;

public class Arduino_Listener_Handler {

	public WebSocketContainer container;
	
	public ArduinoData_Listener arduinoClient;
	
	public String url;
	
	final CountDownLatch messageLatch = new CountDownLatch(1);
	

	public interface EventListenerHandler {
		
		public void onOpen(Session session);
		
		public void onClose(Session session);
		
		public void onMessage(String message, Session session);
		
		public void onError(Throwable t);
		
	}
	
	EventListenerHandler handler;
	
	public Arduino_Listener_Handler(String url, EventListenerHandler handler) {
		this.url = url;
		this.handler = handler;
	}
	
    public void startConnection(){
        
        container = ContainerProvider.getWebSocketContainer();
        
        arduinoClient = new ArduinoData_Listener(new ArduinoData_Listener.EventHandler() {
            @Override
            public void onOpen(Session session) {
                handler.onOpen(session);
            }

            @Override
            public void onClose(Session session) {
            	handler.onClose(session);
            }

            @Override
            public void onMessage(String message, Session session) {
            	handler.onMessage(message, session);
            }

            @Override
            public void onError(Throwable t) {
            	handler.onError(t);
            }
        });
        
        
        try {
            String uri = url;
            System.out.println("Connecting to " + uri);
            container.connectToServer(arduinoClient, URI.create(uri));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
            ex.printStackTrace();
            handler.onError(ex);
        }
        
    }
    
    public void closeConnection() {
        try {
        	arduinoClient.getSession().close();
        } catch (IOException ex) {
            ex.printStackTrace();
            handler.onError(ex);
        }
    }
    
    public boolean isClosed(){
    	if(arduinoClient==null)return true;
        if(arduinoClient.getSession() == null)return true;
        return !arduinoClient.getSession().isOpen();
    }
    
    public void sendMessage(String message) {
    	try {
        	arduinoClient.getSession().getBasicRemote().sendText(message);;
        } catch (IOException ex) {
            ex.printStackTrace();
            handler.onError(ex);
        }
    }

}
