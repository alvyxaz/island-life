package com.friendlyblob.islandlife.server.model.actors;

import com.friendlyblob.islandlife.server.network.GameClient;
import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class Player extends GameCharacter {
	
	private GameClient client = null;

	public Player() {
		super();
		this.setObjectId((int)(Math.random()*2000)); // TODO put a real id
	}

	public void setClient(GameClient client) {
		this.client = client;
	}
	
	public GameClient getClient() {
		return client;
	}
	
	@Override
	public void sendPacket(ServerPacket packet) {
		getClient().sendPacket(packet);
	}
	
}
