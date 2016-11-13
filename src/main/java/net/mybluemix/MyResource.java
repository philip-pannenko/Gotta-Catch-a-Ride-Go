package net.mybluemix;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
	
	private static final Location[] DEMO_LOCATIONS = new Location[17];
	private static final AtomicInteger DEMO_COUNTER = new AtomicInteger(0);
	private static boolean isDemoInitialized = false;
	static {
		if(!isDemoInitialized) {
			DEMO_LOCATIONS[0] = new Location(41.340841f, -72.074378f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[1] = new Location(41.340181f, -72.074618f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[2] = new Location(41.339472f, -72.074822f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[3] = new Location(41.339375f, -72.073814f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[4] = new Location(41.338948f, -72.073921f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[5] = new Location(41.338188f, -72.073651f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[6] = new Location(41.337594f, -72.073332f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[7] = new Location(41.336569f, -72.073307f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[8] = new Location(41.336856f, -72.072732f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[9] = new Location(41.336387f, -72.072898f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[10] = new Location(41.336875f, -72.072924f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[11] = new Location(41.336847f, -72.072094f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[12] = new Location(41.336847f, -72.071227f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[13] = new Location(41.336703f, -72.070334f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[14] = new Location(41.336703f, -72.069415f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[15] = new Location(41.336157f, -72.068075f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
			DEMO_LOCATIONS[16] = new Location(41.335505f, -72.068037f, 0, 999, "demo", Calendar.getInstance().getTime(), false);
		}
		
		ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
		es.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				InMemoryDB.DB.put(999l, DEMO_LOCATIONS[DEMO_COUNTER.getAndIncrement() % 17]);
				if(DEMO_COUNTER.get() > 17) {
					DEMO_COUNTER.set(0);
				}
				InMemoryDB.DB.get(999l).setAge(Calendar.getInstance().getTime());
			}
		}, 0, 15, TimeUnit.SECONDS);
		
	}

	public MyResource() {
		packages(SimpleRest.class.getPackage().getName());
		register(LoggingFeature.class);
		register(JacksonFeature.class);
	}

}