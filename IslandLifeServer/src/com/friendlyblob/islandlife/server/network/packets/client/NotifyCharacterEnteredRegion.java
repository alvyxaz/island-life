package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class NotifyCharacterEnteredRegion extends ServerPacket {

	@Override
	protected void write() {
		writeC(0x05);
	}

}
