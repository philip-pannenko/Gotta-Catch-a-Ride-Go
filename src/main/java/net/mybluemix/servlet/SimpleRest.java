package net.mybluemix.servlet;

import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.mybluemix.InMemoryDB;
import net.mybluemix.bo.Location;

@Path("/")
public class SimpleRest {

	private static Logger log = Logger.getLogger(SimpleRest.class.getName());

	@POST
	@Path("/location")
	@Consumes("application/json")
	public Response setLocation(Location location) {
		if (location == null) {
			return Response.status(Status.BAD_REQUEST).entity("no data").build();
		}
		if (location.getId() == -1) {
			return Response.status(Status.BAD_REQUEST).entity("no token").build();
		}
		location.setAge(Calendar.getInstance().getTime());
		InMemoryDB.DB.put(location.getId(), location);
		log.info("Added Location to DB " + location.toString());
		return Response.status(200).entity("Success").build();
	}

	@GET
	@Path("/locations")
	@Produces("application/json")
	public Collection<Location> getLocations() {
		return InMemoryDB.DB.values();
	}
	
	@GET
	@Path("/location/{param}")
	@Produces("application/json")
	public Location getLocation(@PathParam("param") long id) {
		return InMemoryDB.DB.get(id);
	}


	@GET
	@Path("/sample")
	@Produces("application/json")
	public Location getSample() {
		return new Location(1f, 1f, 1, 1l, "msg", Calendar.getInstance().getTime());
	}

	@GET
	@Path("/token")
	@Produces("application/json")
	public long getToken() {
		return InMemoryDB.ID.getAndIncrement();
	}

}