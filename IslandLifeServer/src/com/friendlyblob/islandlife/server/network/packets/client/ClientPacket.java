package com.friendlyblob.islandlife.server.network.packets.client;

import org.mmocore.network.ReceivablePacket;

import com.friendlyblob.islandlife.server.network.GameClient;
import com.friendlyblob.islandlife.server.network.packets.server.ServerPacket;

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
