package com.friendlyblob.islandlife.server.network.packets;

public class ServerClose extends GameServerPacket {
	
	public static final ServerClose STATIC_PACKET = new ServerClose();
	
	private ServerClose() {
	}
	
	@Override
	protected void writeImpl() {
		writeC(0x20);
	}

}
