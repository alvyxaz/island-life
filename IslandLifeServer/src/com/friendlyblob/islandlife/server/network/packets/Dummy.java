package com.friendlyblob.islandlife.server.network.packets;

import java.io.IOException;

import com.friendlyblob.islandlife.server.network.BaseSendablePacket;

public class Dummy extends BaseSendablePacket{

	public Dummy() {
		writeC(0x09);
		writeD(5);
		writeS("Alvys");
	}
	
	@Override
	public byte[] getContent() {
		return getBytes();
	}
	
}
