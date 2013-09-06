package com.friendlyblob.islandlife.server.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.friendlyblob.islandlife.server.model.actors.GameCharacter;
import com.friendlyblob.islandlife.server.model.actors.Player;

public class World {

	private ConcurrentHashMap<Integer, GameCharacter> allPlayers;
	private ConcurrentHashMap<Integer, GameObject> allObjects;

	private ArrayList<Zone> allRegions;
	
	public World() {
		allPlayers = new ConcurrentHashMap<Integer, GameCharacter>() ;
		allObjects = new ConcurrentHashMap<Integer, GameObject>() ;
		
		// TODO load all zones from somewhere
		allRegions = new ArrayList<Zone>();
		allRegions.add(new Zone());
	}
	
	public void addPlayer(Player player) {
		allPlayers.put(player.getObjectId(), player);
		allObjects.put(player.getObjectId(), player);
		
		// TODO add player to it's zone, not a random zone
		allRegions.get(0).addPlayer(player);
	}
	
	public static final class SingletonHolder {
		public final static World INSTANCE = new World();
	}
	
	public static World getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
