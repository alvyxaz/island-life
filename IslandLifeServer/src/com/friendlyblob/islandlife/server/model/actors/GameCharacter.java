package com.friendlyblob.islandlife.server.model.actors;

import com.friendlyblob.islandlife.server.GameTimeController;
import com.friendlyblob.islandlife.server.model.GameObject;
import com.friendlyblob.islandlife.server.model.stats.BaseStats;
import com.friendlyblob.islandlife.server.model.stats.CharacterStats;
import com.friendlyblob.islandlife.server.model.stats.StatsSet;
import com.friendlyblob.islandlife.server.network.GameClient;
import com.friendlyblob.islandlife.server.network.packets.ClientPacket;
import com.friendlyblob.islandlife.server.network.packets.ServerPacket;
import com.friendlyblob.islandlife.server.utils.ObjectPosition;

/*
 * Parent of players and npc's
 */
public class GameCharacter extends GameObject{

	private BaseStats baseStats;
	private CharacterStats stats;
	
	private MovementData movement;
	
	private GameClient client = null;
	
	public GameCharacter() {
		// Initialize base stats
		
		// TODO Use a template or something to base stats of
		StatsSet set = new StatsSet();
		set.set("baseWalkingSpeed", 100);
		set.set("baseRunningSpeed", 200);
		baseStats = new BaseStats(set);
	
		// Initialize stats
		stats = new CharacterStats(this);
	}
	
	/**
	 * Updating character position.
	 * @param gameTicks current game tick
	 * @return true if character has reached its destination
	 */
	public boolean updatePosition (int gameTicks) {
		float prevX = getPosition().getX();
		float prevY = getPosition().getY();
		
		float angle = (float) Math.atan2(movement.destinationY - prevY, movement.destinationX - prevX);
		
		// TODO check if running or walking
		int movementSpeed = getMovementSpeed();
		float distanceCovered = GameTimeController.DELTA_TIME*movementSpeed;
		
		getPosition().offset((float) Math.cos(angle) * distanceCovered, 
				(float) Math.sin(angle) * distanceCovered);
		
		// Check if destination is reached
		int dX = (int)(movement.destinationX - getPosition().getX());
		int dY = (int)(movement.destinationY - getPosition().getY());
		
		if (dX * dX + dY * dY < distanceCovered * distanceCovered) {
			movement = null;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Registers a new movement destination in <b>movement</b> variable
	 * Called only once when user requests to move a character.
	 * 
	 * Actions:
	 * 	Checking for collisions
	 * 	If character is about to collide with something, move it to collision point
	 * 
	 * @param x requested destination x
	 * @param y requested destination y
	 * @return true if movement is initialized, false if impossible to move
	 */
	public boolean moveCharacterTo(int x, int y) {
		// TODO check boundaries and collisions. If out of bounds - return false
		
		movement = new MovementData();
		movement.destinationX = x;
		movement.destinationY = y;
		movement.timeStamp = GameTimeController.getInstance().getGameTicks();
		
		GameTimeController.getInstance().registerMovingObject(this);
		
		return true;
	}
	
	public CharacterStats getStats() {
		return stats;
	}
	
	public static class MovementData {
		public float destinationX;
		public float destinationY;
		public int timeStamp;
	}
	
	public MovementData getMovement() {
		return movement;
	}
	
	public BaseStats getBaseStats() {
		return baseStats;
	}
	
	public int getMovementSpeed() {
		// TODO Check whether running or walking, and return what's necessary
		return getWalkingSpeed();
	}
	
	private int getWalkingSpeed() {
		return stats.getWalkingSpeed();
	}
	
	public void setClient(GameClient client) {
		this.client = client;
	}
	
	public GameClient getClient() {
		return client;
	}
	
	public void sendPacket(ServerPacket packet) {
		
	}
	
}
