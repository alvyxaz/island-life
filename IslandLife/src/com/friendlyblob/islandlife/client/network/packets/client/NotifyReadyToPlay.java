package com.friendlyblob.islandlife.client.network.packets.client;

import com.friendlyblob.islandlife.client.network.packets.SendablePacket;

public class NotifyReadyToPlay extends SendablePacket {

	@Override
	public void write() {
		writeC(0x01);
	}

}
