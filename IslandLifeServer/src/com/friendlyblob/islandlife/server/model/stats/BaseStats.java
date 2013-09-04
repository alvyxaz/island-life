package com.friendlyblob.islandlife.server.model.stats;

public class BaseStats {

	public final int baseWalkingSpeed;
	public final int baseRunningSpeed;
	
	public BaseStats(StatsSet set) {
		baseWalkingSpeed = set.getInteger("baseWalkingSpeed", 100);
		baseRunningSpeed = set.getInteger("baseRunningSpeed", 200);
	}
	
}
