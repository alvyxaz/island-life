package com.friendlyblob.islandlife.server.model;

import com.friendlyblob.islandlife.server.utils.ObjectPosition;

public class GameObject {
	
	private int objectId;
	private ObjectPosition position;
	
	private Region region;
	private Zone zone;
	
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public Zone getZone() {
		return zone;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public GameObject() {
		position = new ObjectPosition();
	}
	
	public ObjectPosition getPosition() {
		return position;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
	public void setObjectId(int id) {
		this.objectId = id;
	}
	
}
