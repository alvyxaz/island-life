package com.friendlyblob.islandlife.client.network.packets.client;

import com.friendlyblob.islandlife.client.network.packets.SendablePacket;

public class LoginPacket extends SendablePacket{

	@Override
	public void write() {
		writeC(0x02);
		writeS("Prisijungimas");
		writeS("Slaptazodis");
	}

}
