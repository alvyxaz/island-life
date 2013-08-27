package com.friendlyblob.islandlife.server.network.packets.server;


public class ActionFailed extends ServerPacket{
	public static final ActionFailed STATIC_PACKET = new ActionFailed();

	@Override
	protected void write() {
		writeC(0x1f);
	}
}
