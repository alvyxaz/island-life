package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.entities.Player;
import com.friendlyblob.islandlife.client.gameworld.GameWorld;
import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;
import com.friendlyblob.islandlife.client.network.packets.client.NotifyReadyToPlay;

public class LoginSuccessful extends ReceivablePacket{

	int playerId;
	int x;
	int y;
	
	@Override
	public boolean read() {
		playerId = readD();
		x = readD();
		y = readD();
		return true;
	}

	@Override
	public void run() {
		// Setting up a player
		Player player = GameWorld.getInstance().player;
		player.objectId = playerId;
		player.setPosition(x, y);
		
		// Notifying server that client is ready to play
		MyGame.connection.sendPacket(new NotifyReadyToPlay());
	}

}
