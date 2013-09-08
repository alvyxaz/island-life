package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.gameworld.GameWorld;
import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;

public class CharacterLeftRegion extends ReceivablePacket {

	private int characterId;
	
	@Override
	public boolean read() {
		characterId = readD();
		return true;
	}

	@Override
	public void run() {
		GameWorld.getInstance().removeCharacter(characterId);
	}

}
