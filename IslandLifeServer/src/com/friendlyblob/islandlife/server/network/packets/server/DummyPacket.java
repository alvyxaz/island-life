package com.friendlyblob.islandlife.server.network.packets.server;

public class DummyPacket extends ServerPacket{

	@Override
	protected void write() {
		writeC(0x5F);
		writeD(5);
	}

}
