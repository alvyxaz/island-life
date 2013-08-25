package com.friendlyblob.islandlife.server.network.packets;

public class ActionFailed extends GameServerPacket{
	public static final ActionFailed STATIC_PACKET = new ActionFailed();
	
	private ActionFailed() {
	}
	
	@Override
	protected void writeImpl() {
		writeC(0x1f);
	}
}
