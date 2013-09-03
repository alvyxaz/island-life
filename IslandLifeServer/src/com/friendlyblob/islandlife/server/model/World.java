package com.friendlyblob.islandlife.server.model;

import java.util.concurrent.ConcurrentHashMap;

public class World {

	private ConcurrentHashMap<Integer, Character> allPlayers;
	private ConcurrentHashMap<Integer, GameObject> allObjects;
	
}
