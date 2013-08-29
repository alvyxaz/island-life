package com.friendlyblob.islandlife.server.network.packets;

import org.mmocore.network.ReceivablePacket;

import com.friendlyblob.islandlife.server.network.GameClient;

public abstract class ClientPacket extends ReceivablePacket<GameClient> {

	
	
	/**
	 * Sends a game server packet to the client.
	 * @param gsp the game server packet
	 */
	protected final void sendPacket(ServerPacket sp)
	{
		getClient().sendPacket(sp);
	}

}
