package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.gameworld.GameWorld;
import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;
import com.friendlyblob.islandlife.client.network.packets.client.NotifyReadyToPlay;

public class LoginSuccessful extends ReceivablePacket{

	int playerId;
	
	@Override
	public boolean read() {
		playerId = readD();
		return true;
	}

	@Override
	public void run() {
		// Setting up a player
		GameWorld.getInstance().player.objectId = playerId;
		
		// Notifying server that client is ready to play
		MyGame.connection.sendPacket(new NotifyReadyToPlay());
	}

}
