package com.friendlyblob.islandlife.server;

/*
 * SQL database connection handler
 */
public class DatabaseFactory {
	
	public static DatabaseFactory getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	public static class SingletonHolder{
		public static final DatabaseFactory INSTANCE = new DatabaseFactory(); 
	}
	
}
