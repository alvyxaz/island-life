package com.friendlyblob.islandlife.client.entities;

import com.badlogic.gdx.math.Rectangle;

public class MovableObject {

	public Rectangle hitBox;
	
	private int targetX;
	private int targetY;
	
	private int speed = 200; // Pixels per second
	
	// States
	private int state = 0;
	private final int IDLE = 0;
	private final int MOVING = 1;
	
	public MovableObject(int x, int y){
		hitBox = new Rectangle(x, y, 32, 64);
	}
	
	public void update(float deltaTime) {
		switch (state) {
		case IDLE :
			break;
		case MOVING:
			float angle = (float)Math.atan2(targetY - hitBox.y, targetX - hitBox.x);
			
			// Moving 
			hitBox.x += Math.cos(angle) * speed * deltaTime;
			hitBox.y += Math.sin(angle) * speed * deltaTime;
			
			// Checking whether object has arrived
			if (Math.abs(targetX - hitBox.x) < speed * deltaTime && Math.abs(targetY - hitBox.y) < speed * deltaTime) {
				state = IDLE;
			}
			
			break;
		}
	}
	
	public void moveTo (int x, int y) {
		this.targetX = x - (int) hitBox.width/2;
		this.targetY = y;
		state = MOVING;
	}
}