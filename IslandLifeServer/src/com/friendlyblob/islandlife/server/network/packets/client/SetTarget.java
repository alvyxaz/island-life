package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.model.GameObject;
import com.friendlyblob.islandlife.server.model.World;
import com.friendlyblob.islandlife.server.network.packets.ClientPacket;

public class SetTarget extends ClientPacket {

	int id;
	
	@Override
	protected boolean read() {
		this.id = readD();
		return true;
	}

	@Override
	public void run() {
		// No need to check if null, because it will still be valid
		GameObject object = World.getInstance().getObject(id);
		getClient().getPlayer().setTarget(object);
	}
	
}
