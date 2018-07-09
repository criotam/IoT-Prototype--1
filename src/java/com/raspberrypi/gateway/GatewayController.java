/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raspberrypi.gateway;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author AVINASH
 */
@Path("/gateway_controller")
public class GatewayController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GatewayController
     */
    public GatewayController() {
    }

    /**
     * Retrieves representation of an instance of com.raspberrypi.gateway.GatewayController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GatewayController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @Path("/network")
    @POST
    @Consumes("text/plain")
    @Produces("application/json")
    public String postNetworkParameters(@FormParam("ssid")String ssid, @FormParam("password")String password)
    {
        //connect to wifi
        return("ok");
    }

}
