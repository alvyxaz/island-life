package com.friendlyblob.islandlife.client.network.packets;

public class DummyPacket extends SendablePacket {

	@Override
	public void write() {
		writeC(0x0C);
		writeS("Veikia");
	}

}