package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.gameworld.GameWorld;
import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;

public class LoginSuccessful extends ReceivablePacket{

	int playerId;
	
	@Override
	public boolean read() {
		playerId = readD();
		return true;
	}

	@Override
	public void run() {
		System.out.println("Setting player id");
		GameWorld.getInstance().player.objectId = playerId;
	}

}
