package com.friendlyblob.islandlife.server.utils;

/**
 * Represent object's current position
 */
public class ObjectPosition {
	private int x;
	private int y;
	
	public ObjectPosition() {
		x = 0;
		y = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY() {
		this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void offset(int dX, int dY) {
		this.x += dX;
		this.y += dY;
	}
}
