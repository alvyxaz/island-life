package com.friendlyblob.islandlife.server.network.packets.server;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class LoginSuccessful extends ServerPacket {

	private LoginSuccessful () {
		
	}
	
	@Override
	protected void write() {
		writeC(0x03);
	}

}
