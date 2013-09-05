package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.model.actors.Player;
import com.friendlyblob.islandlife.server.network.packets.ClientPacket;
import com.friendlyblob.islandlife.server.network.packets.server.MoveResponse;

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
			
			// Sending a movement response to player
			getClient().sendPacket(new MoveResponse((int)player.getMovement().destinationX,
					(int) player.getMovement().destinationY, 
					player.getMovementSpeed()));
			
		} else {
			// TODO Send a packet of failed action
		}
	}

}
