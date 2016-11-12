package net.mybluemix.servlet;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import net.mybluemix.bo.Location;

@Path("/")
public class SimpleRest {
	
	private static Logger log = Logger.getLogger(SimpleRest.class.getName());

	@POST
	@Consumes("application/json")
	public Response setLocation(Location location) {
		log.info(location.toString());
		return Response.status(200).entity("Success").build();
	}
	

	@GET
	@Produces("application/json")
	public Location getLocation() {
		return new Location();
	}
	

}