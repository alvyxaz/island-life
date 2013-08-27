package com.friendlyblob.islandlife.server.network.packets.server;

import java.nio.ByteBuffer;

import org.mmocore.network.SendablePacket;

import com.friendlyblob.islandlife.server.network.GameClient;

public abstract class ServerPacket extends SendablePacket<GameClient>{

	public void runImpl()
	{
		
	}
	
	public ByteBuffer getBuf(){
		return _buf;
	}
}
