package net.mybluemix;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import net.mybluemix.servlet.SimpleRest;

@ApplicationPath("/rest")
public class MyResource extends ResourceConfig {

	static {
		Logger rootLogger = Logger.getLogger("");
		for (Handler handler : rootLogger.getHandlers()) {
			handler.setLevel(Level.INFO);
		}
		rootLogger.setLevel(Level.INFO);
	}

	public MyResource() {
		packages(SimpleRest.class.getPackage().getName());
		register(LoggingFeature.class);
		register(JacksonFeature.class);
	}

}