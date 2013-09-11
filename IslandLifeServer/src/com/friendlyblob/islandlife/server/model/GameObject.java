package com.friendlyblob.islandlife.server.model;

import java.util.List;

import javolution.util.FastList;
import javolution.util.FastMap;

import com.friendlyblob.islandlife.server.utils.ObjectPosition;

/**
 * Represents an instance of the object (Player, mob, item in the world and etc.)
 * @author Alvys
 *
 */
public class GameObject {
	
	private int objectId;
	private ObjectPosition position;
	
	private Region region;
	private Zone zone;
	
	private GameObject target;
	
	private List<GameObject> targetedBy = new FastList<GameObject>().shared();
	
	/**
	 * Sets objects target to a given object 
	 * (Player targeting Player, Player targeting object ant etc.)
	 * @param object Object to set target to
	 */
	public void setTarget(GameObject object) {
		this.target = object;
		object.addTargetedBy(this);
	}
	
	/**
	 * Removes a target from this character, and removes
	 * targetedBy of it's target
	 */
	public void removeTarget() {
		this.target.removeTargetedBy(this);
		this.target = null;
	}
	
	/**
	 * Adds a game object that is targeting current object
	 * @param object GameObject that targets current object
	 */
	public void addTargetedBy(GameObject object) {
		targetedBy.add(object);
	}
	
	/**
	 * Removes a game object that is no longer targeting current object
	 * @param object
	 */
	public void removeTargetedBy(GameObject object) {
		targetedBy.remove(object);
	}
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	public void clearTargetedBy() {
		for (GameObject object : targetedBy) {
			object.removeTarget();
		}
		targetedBy.clear();
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
