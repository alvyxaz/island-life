package com.friendlyblob.islandlife.server.network.packets.server;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class MoveResponse extends ServerPacket{

	private int x;
	private int y;
	private int speed;
	
	public MoveResponse(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	@Override
	protected void write() {
		writeC(0x03);
		writeD(speed);
		writeD(x);
		writeD(y);
	}

}
