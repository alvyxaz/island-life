package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.network.packets.ClientPacket;

public class RequestMove extends ClientPacket{

	private int x;
	private int y;
	
	@Override
	protected boolean read() {
		x = readD();
		y = readD();
		return true;
	}

	@Override
	public void run() {
		// If failed to move
		if(getClient().getPlayer().moveCharacterTo(x, y)) {
			// TODO Send a packet of successful action
		} else {
			// TODO Send a packet of failed action
		}
	}

}
