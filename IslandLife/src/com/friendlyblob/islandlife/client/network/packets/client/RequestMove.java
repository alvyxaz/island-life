package com.friendlyblob.islandlife.client.network.packets.client;

import com.friendlyblob.islandlife.client.network.packets.SendablePacket;

public class RequestMove extends SendablePacket {

	private int x;
	private int y;
	
	public RequestMove(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	@Override
	public void write() {
		writeC(0x01);
		writeD(x);
		writeD(y);
	}

}
