package raspberrypi;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class Arduino_Listener_Handler {

	public WebSocketContainer container;
	
	public ArduinoData_Listener arduinoClient;
	
	public String url;
	
	final CountDownLatch messageLatch = new CountDownLatch(1);
	
	public Arduino_Listener_Handler(String url) {
		this.url = url;
	}
	
    public void startConnection(){
        
        container = ContainerProvider.getWebSocketContainer();
        arduinoClient = new ArduinoData_Listener();
        
        try {
            String uri = url;
            System.out.println("Connecting to " + uri);
            container.connectToServer(arduinoClient, URI.create(uri));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void closeConnection() {
        try {
        	arduinoClient.getSession().close();
        } catch (IOException ex) {
            ex.printStackTrace();
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
        }
    }

}
