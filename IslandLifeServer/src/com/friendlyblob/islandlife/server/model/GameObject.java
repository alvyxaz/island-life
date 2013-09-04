package com.friendlyblob.islandlife.server.model;

import com.friendlyblob.islandlife.server.utils.ObjectPosition;

public class GameObject {
	
	private int objectId;
	private ObjectPosition position;
	
	public GameObject() {
		position = new ObjectPosition();
	}
	
	public ObjectPosition getPosition() {
		return position;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
}
