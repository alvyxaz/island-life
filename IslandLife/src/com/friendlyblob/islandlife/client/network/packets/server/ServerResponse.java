package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;

public class ServerResponse extends ReceivablePacket{

	private String response;
	
	@Override
	public boolean read() {
		int lala = readD();
		return true;
	}

	@Override
	public void run() {
		System.out.println("RUNNING RECEIVED PACKET: " + response);
	}

}
