package com.friendlyblob.islandlife.client.network;

import java.nio.ByteBuffer;

import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;
import com.friendlyblob.islandlife.client.network.packets.server.*;

public class PacketHandler {

	public ReceivablePacket handlePacket(ByteBuffer buf) {
		// Operation code
		int opcode = buf.get() & 0xFF;
		ReceivablePacket response = null;
		
		switch(opcode){
			case 0x01:
				response = new KeyPacket();
				break;
			case 0x02:
				response = new LoginSuccessful();
				break;
			case 0x03:
				response = new MoveResponse();
				break;
			case 0x04:
				response = new CharactersInRegion();
				break;
			default:
				response = new UnknownPacket();
				break;
		}
		
		
		return response;
	} 
	
}
