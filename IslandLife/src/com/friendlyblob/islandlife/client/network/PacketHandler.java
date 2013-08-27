package com.friendlyblob.islandlife.client.network;

import java.nio.ByteBuffer;

import com.friendlyblob.islandlife.client.network.packets.ReceivablePacket;
import com.friendlyblob.islandlife.client.network.packets.server.ServerResponse;

public class PacketHandler {

	public ReceivablePacket handlePacket(ByteBuffer buf) {

		// Operation code
		int opcode = buf.get() & 0xFF;
		
		ReceivablePacket response = null;
		
		switch(opcode){
			case 0x5F:
				response = new ServerResponse();
				break;
		}
		
		return response;
	} 
	
}
