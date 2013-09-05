package com.friendlyblob.islandlife.server.model.actors;

import com.friendlyblob.islandlife.server.GameTimeController;
import com.friendlyblob.islandlife.server.model.GameObject;
import com.friendlyblob.islandlife.server.model.stats.BaseStats;
import com.friendlyblob.islandlife.server.model.stats.CharacterStats;
import com.friendlyblob.islandlife.server.model.stats.StatsSet;
import com.friendlyblob.islandlife.server.utils.ObjectPosition;

/*
 * Parent of players and npc's
 */
public class GameCharacter extends GameObject{

	private BaseStats baseStats;
	private CharacterStats stats;
	
	private MovementData movement;
	
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
		int movementSpeed = getWalkingSpeed();
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
	
	public BaseStats getBaseStats() {
		return baseStats;
	}
	
	public int getWalkingSpeed() {
		return stats.getWalkingSpeed();
	}
	
}
