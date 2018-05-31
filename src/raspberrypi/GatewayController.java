package raspberrypi;


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

	@POST
	@Path("/network")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String network(@FormParam("ssid") String ssid, @FormParam("password") String password)
    {
        //connect to wifi
        return("ok");
    }

}
