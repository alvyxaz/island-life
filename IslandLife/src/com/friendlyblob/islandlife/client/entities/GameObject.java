package com.friendlyblob.islandlife.client.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.friendlyblob.islandlife.client.helpers.Assets;

public abstract class GameObject {
	
	public int objectId;
	
	public int x;
	public int y;
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(SpriteBatch sb);
}
