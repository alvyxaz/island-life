package com.friendlyblob.islandlife.server.model.actors;

import com.friendlyblob.islandlife.server.model.GameObject;
import com.friendlyblob.islandlife.server.model.stats.BaseStats;
import com.friendlyblob.islandlife.server.model.stats.CharacterStats;
import com.friendlyblob.islandlife.server.model.stats.StatsSet;

/*
 * Parent of players and npc's
 */
public class GameCharacter extends GameObject{

	private BaseStats baseStats;
	private CharacterStats stats;
	
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
		return false;
	}
	
	public BaseStats getBaseStats() {
		return baseStats;
	}
	
	public int getWalkingSpeed() {
		return stats.getWalkingSpeed();
	}
	
	public CharacterStats getStats() {
		return stats;
	}
}
