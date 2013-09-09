package com.friendlyblob.islandlife.server.network.packets.server;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class CharacterLeft extends ServerPacket{

	private int characterId;
	
	public CharacterLeft(int id) {
		characterId = id;
	}
	
	@Override
	protected void write() {
		writeC(0x05);
		writeD(characterId);
	}

}
