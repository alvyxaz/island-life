package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.network.packets.server.DummyPacket;

public class ResponseTest extends ClientPacket {

	private String fromClient;
	
	@Override
	public boolean read() {
		fromClient = readS();
		return true;
	}

	@Override
	public void run() {
		System.out.println("Running ResponseTest run() method. From client: " + fromClient);
		this.getClient().sendPacket(new DummyPacket());
	}

}
