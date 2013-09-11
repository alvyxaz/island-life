package com.friendlyblob.islandlife.server.network.packets.server;

import java.util.List;

import com.friendlyblob.islandlife.server.model.GameObject;
import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class ObjectsInRegion extends ServerPacket {

	private List<GameObject> closeObjects;
	
	public ObjectsInRegion(List<GameObject> objects) {
		this.closeObjects = objects;
	}
	
	@Override
	protected void write() {
		writeC(0x06);
		
		writeH(closeObjects.size()); // Object count
		
		for(GameObject object: closeObjects) {
			// TODO save object data
		}
	}

}
