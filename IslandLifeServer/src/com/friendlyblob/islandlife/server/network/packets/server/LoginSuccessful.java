package com.friendlyblob.islandlife.server.network.packets.server;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class LoginSuccessful extends ServerPacket {

	private int playerId;
	
	private LoginSuccessful (int id) {
		this.playerId = id;
	}
	
	@Override
	protected void write() {
		writeC(0x02);
		writeD(playerId);
	}

}
