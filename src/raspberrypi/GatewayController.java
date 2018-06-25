package raspberrypi;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/GatewayController")
public class GatewayController{
	
	@GET
    @Path("/{msg}")
    @Produces(MediaType.TEXT_HTML)
    public String printMsg(@PathParam("msg") String msg){
        return "<h1>Your message: "+msg+"</h1>";
    }   

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_HTML)
    public String getStatus(){

        String status = "{";

        if(BroadCastListener.gateway1_handler_lc!=null){
            if(!BroadCastListener.gateway1_handler_lc.isClosed()){
                status += " gateway1_lc: connected";
            }else{
                status += " gateway1_lc: disconnected";
            }

        }else{
            status += " gateway1_lc: disconnected";
        }

        if(BroadCastListener.gateway2_handler_lc!=null){
            if(!BroadCastListener.gateway2_handler_lc.isClosed()){
                status += " ,gateway2_lc: connected";
            }else{
                status += " ,gateway2_lc: disconnected";
            }

        }else{
            status += " ,gateway2_lc: disconnected";
        }

        if(BroadCastListener.gateway2_handler_emg!=null){
            if(!BroadCastListener.gateway2_handler_emg.isClosed()){
                status += " ,gateway2_emg: connected";
            }else{
                status += " ,gateway2_emg: disconnected";
            }

        }else{
            status += " ,gateway2_emg: disconnected";
        }

        if(BroadCastListener.gateway3_handler_fp!=null){
            if(!BroadCastListener.gateway3_handler_fp.isClosed()){
                status += " ,gateway3_fp: connected";
            }else{
                status += " ,gateway3_fp: disconnected";
            }

        }else{
            status += " ,gateway3_fp: disconnected";
        }

        if(BroadCastListener.gateway3_handler_emg!=null){
            if(!BroadCastListener.gateway3_handler_emg.isClosed()){
                status += " ,gateway3_emg: connected";
            }else{
                status += " ,gateway3_emg: disconnected";
            }

        }else{
            status += " ,gateway3_emg: disconnected";
        }

        status += " }";
        return "<h1>"+status+"</h1>";
    }   

	@POST
	@Path("/network")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String network(@FormParam("ssid") String ssid, @FormParam("password") String password)
    {
        //connect to wifi
		connectToWifi(ssid, password);
        return("sudo ./wifi_connect.sh "+ssid+" "+password);
    }
	
	@POST
	@Path("/start_hotspot")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String start_hotspot()
    {
        hotspot_start();
        return("ok");
    }
	
	
	@POST
	@Path("/start_tomcat")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String start_tomcat()
    {
        tomcat_start();
        return("ok");
    }
	
	
	@POST
	@Path("/stop_tomcat")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String stop_tomcat()
    {
        tomcat_stop();
        return("ok");
    }
	
	public void connectToWifi(String ssid, String password) {
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("sudo /home/pi/wifi_connect.sh "+ssid+" "+password);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public void hotspot_start() {
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("sudo /home/pi/hotspot_start.sh");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public void tomcat_start() {
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("sudo /home/pi/start_tomcat.sh");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public void tomcat_stop() {
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("sudo /home/pi/stop_tomcat.sh");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

}
