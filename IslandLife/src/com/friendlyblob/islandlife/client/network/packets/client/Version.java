package com.friendlyblob.islandlife.client.network.packets.client;

import com.friendlyblob.islandlife.client.network.packets.SendablePacket;

public class Version extends SendablePacket {

	private int version;
	
	public Version(int version) {
		this.version = version;
	}
	
	@Override
	public void write() {
		writeC(0x01);
		writeD(version); // TODO add a real version
	}
	
}
