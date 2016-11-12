package net.mybluemix;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import net.mybluemix.bo.Location;
import net.mybluemix.servlet.SimpleRest;

@ApplicationPath("/rest")
public class MyResource extends ResourceConfig {

	public static Logger log = Logger.getLogger(MyResource.class.getName());

	static {
		Logger rootLogger = Logger.getLogger("");
		for (Handler handler : rootLogger.getHandlers()) {
			handler.setLevel(Level.INFO);
		}
		rootLogger.setLevel(Level.INFO);
	}

	static {
		ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
		es.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {

				Calendar time = Calendar.getInstance();
				time.add(Calendar.SECOND, -30);
				long timeInMillis = time.getTimeInMillis();

				Iterator<Location> it = InMemoryDB.DB.values().iterator();

				while (it.hasNext()) {
					Location loc = it.next();
					if (loc.getAge().getTime() < timeInMillis) {
						log.info("Deleting Location ID " + loc.getId());
						InMemoryDB.DB.remove(loc.getId());
					}
				}
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public MyResource() {
		packages(SimpleRest.class.getPackage().getName());
		register(LoggingFeature.class);
		register(JacksonFeature.class);
	}

}