package com.friendlyblob.islandlife.server;

import java.util.Calendar;

public class GameTimeController extends Thread{
	
	public static final int TICKS_PER_SECOND = 10;
	public static final int MILLIS_IN_TICK = 1000 / TICKS_PER_SECOND;
	
	private static GameTimeController instance;
	
	private final long referenceTime;
	
	/**
	 * Initializing by creating an instance for GameTimeController
	 */
	public static void initialize() {
		instance = new GameTimeController();
	}
	
	public GameTimeController() {
		super("GameTimeController");
		referenceTime = System.currentTimeMillis();
		
		super.start();
	}
	
	/**
	 * Returns a calculated number of ticks passed since the server start. This
	 * tick represents a current tick of the game.
	 */
	public final int getGameTicks() {
		return (int) ((System.currentTimeMillis() - referenceTime) / MILLIS_IN_TICK);
	}
	
	public final void run() {
		long nextTickTime, sleepTime;
		
		while (true) {
			nextTickTime = ((System.currentTimeMillis() / MILLIS_IN_TICK) * MILLIS_IN_TICK) + MILLIS_IN_TICK;
			
			// Most of the magic related to a tick happens here.

			sleepTime = nextTickTime - System.currentTimeMillis();
			
			// Sleeping until next tick
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (final InterruptedException e) {
					
				}
			}
		}
	}
	
	public final void stopTimer() {
		super.interrupt();
	}
	
}
