package com.friendlyblob.islandlife.server.network.packets.server;

import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class KeyPacket extends ServerPacket {

	byte [] key;
	
	public KeyPacket( byte [] key) {
		this.key = key;
	}
	
	@Override
	protected void write() {
		writeC(0x02);
		for(int i = 0; i < 8; i++){
			writeC(key[i]);
		}
	}
	
}
