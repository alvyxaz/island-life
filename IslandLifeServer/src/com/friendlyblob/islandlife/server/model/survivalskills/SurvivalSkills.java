package com.friendlyblob.islandlife.server.model.survivalskills;

public class SurvivalSkills {
	private int level;
	private int currentExp;
	private int xpToLevel;
	
	public static enum SurvivalSkillType {
		WOODCUTTING,
		FIREMAKING,
		GATHERING,
		COOKING,
		MINING,
		TREASURE_HUNTING,
	}
}
