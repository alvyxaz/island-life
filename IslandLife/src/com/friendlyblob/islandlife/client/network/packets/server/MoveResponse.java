package com.friendlyblob.islandlife.client.network.packets.server;

import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;

public class MoveResponse extends ReceivablePacket {

	private int speed;
	private int x;
	private int y;
	
	@Override
	public boolean read() {
		speed = readD();
		x = readD();
		y = readD();
		return true;
	}

	@Override
	public void run() {
		// TODO some sort of static interface is necessary
		this.getConnection().game.screenGame.getWorld().getPlayer().moveTo(x, y, speed);
	}

}
