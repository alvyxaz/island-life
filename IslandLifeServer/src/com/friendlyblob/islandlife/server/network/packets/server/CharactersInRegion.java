package com.friendlyblob.islandlife.server.network.packets.server;

import java.util.List;

import com.friendlyblob.islandlife.server.model.actors.GameCharacter;
import com.friendlyblob.islandlife.server.network.packets.ServerPacket;

public class CharactersInRegion extends ServerPacket{

	List<GameCharacter> closeCharacters;
	
	public CharactersInRegion (List<GameCharacter> list) {
		closeCharacters = list;
	}
	
	@Override
	protected void write() {
		writeC(0x04);
		
		writeH(closeCharacters.size()); // Player count

		// Writing characters
		for(GameCharacter character : closeCharacters) {
			writeD(character.getObjectId());				// Object id
			writeD((int)character.getPosition().getX());	// X position
			writeD((int)character.getPosition().getY());	// X position
			writeD(character.getMovementSpeed());			// Movement speed
		}
	}

}
