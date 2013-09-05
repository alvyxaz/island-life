package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;

public class UnknownPacket extends ReceivablePacket{

	@Override
	public boolean read() {
		return false;
	}

	@Override
	public void run() {
	}

}
