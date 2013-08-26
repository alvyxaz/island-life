package com.friendlyblob.islandlife.server;

import java.util.logging.Level;

/*
 * SQL database connection handler
 */
public class DatabaseFactory {
	
	public static DatabaseFactory getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	/**
	 * Shutdown.
	 */
	public void shutdown()
	{
		// TODO: Shutdown connection
	}
	
	public static class SingletonHolder{
		public static final DatabaseFactory INSTANCE = new DatabaseFactory(); 
	}
	
}
