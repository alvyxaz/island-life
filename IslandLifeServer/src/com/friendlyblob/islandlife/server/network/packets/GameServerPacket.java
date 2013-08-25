package com.friendlyblob.islandlife.server.network.packets;

import org.mmocore.network.SendablePacket;

import com.friendlyblob.islandlife.server.network.GameClient;

public abstract class GameServerPacket extends SendablePacket<GameClient>{
	
	@Override
	protected void write()
	{
		try {
			writeImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runImpl() {
		
	}
	
	protected abstract void writeImpl();

}
