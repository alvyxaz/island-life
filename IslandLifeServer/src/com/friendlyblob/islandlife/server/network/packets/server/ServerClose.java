package com.friendlyblob.islandlife.server.network.packets.server;


public class ServerClose extends ServerPacket {
	
	public static final ServerClose STATIC_PACKET = new ServerClose();
	
	private ServerClose() {
	}

	@Override
	protected void write() {
		writeC(0x20);
	}

}
