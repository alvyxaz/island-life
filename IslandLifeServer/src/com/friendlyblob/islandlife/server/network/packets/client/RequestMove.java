package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.model.actors.Player;
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
		if(getClient().getPlayer().moveCharacterTo(x, y)) {
			Player player = getClient().getPlayer();
			// TODO Have in mind that player might change speed while moving
		} else {
			// TODO Send a packet of failed action
		}
	}

}
