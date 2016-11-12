package net.mybluemix;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import net.mybluemix.bo.Location;

public class InMemoryDB {
	public static AtomicLong ID = new AtomicLong(1);
	public static Map<Long, Location> DB = new ConcurrentHashMap <Long, Location>();
}
