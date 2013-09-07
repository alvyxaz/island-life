package com.friendlyblob.islandlife.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.friendlyblob.islandlife.server.model.actors.GameCharacter;
import com.friendlyblob.islandlife.server.model.actors.Player;
import com.friendlyblob.islandlife.server.network.packets.ServerPacket;
import com.friendlyblob.islandlife.server.network.packets.server.CharactersInRegion;

import javolution.util.FastMap;

/**
 * Represents a part of a zone. This class is mainly for optimization purposes.
 * Client should not be able to notice when he's passing from one region to another.
 * @author Alvys
 *
 */
public class Region {

	private FastMap<Integer, GameCharacter> characters;
	
	private Region [] closeRegions;
	
	public int regionX;
	public int regionY;
	
	public Region(int x, int y) {
		regionX = x;
		regionY = y;
		characters = new FastMap<Integer, GameCharacter>().shared();
		closeRegions = new Region [0];
	}
	
	public void removeCharacter(GameCharacter character) {
		characters.remove(character.getObjectId());
	}
	
	/**
	 * Adds a character to the region. Also, sets character's region to current region
	 * @param character
	 */
	public void addCharacter(GameCharacter character) {
		character.setRegion(this);
		characters.put(character.getObjectId(), character);
	}
	
	/**
	 * Sends a packet to characters in all nearby regions, including itself
	 * @param packet
	 */
	public void broadcastToCloseRegions(ServerPacket packet) {
		broadcast(packet); // Broadcast to itself
		
		// Broadcast to close packet
		for(int i = 0; i < closeRegions.length; i++) {
			closeRegions[i].broadcast(packet);
		}
	}

	/**
	 * Sends data about nearby characters to all players
	 */
	public void updateNearbyPlayersData() {
		// If there's no one to update data far
		if (this.characters.size() == 0 ) return;
		
		List<GameCharacter> visibleCharacters = getVisibleCharacters();
		
		if (characters.size() > 0) {
			CharactersInRegion packet = new CharactersInRegion(visibleCharacters);
			broadcast(packet);
		}
		 
	}
	
	/**
	 * Sends a packet to characters in this region
	 * @param packet
	 */
	public void broadcast(ServerPacket packet) {
		for(GameCharacter character : characters.values()) {
			character.sendPacket(packet);
		}
	}
	
	/**
	 * Returns a list of characters that are in nearby regions
	 * @return visible characters in a List
	 */
	public List<GameCharacter> getVisibleCharacters() {
		List<GameCharacter> visibleCharacters = new ArrayList<GameCharacter>();
		
		// Current region
		for (GameCharacter character : characters.values()) {
			visibleCharacters.add(character);
		}
		
		// Nearby regions
		for(int i = 0; i < closeRegions.length; i++) {
			for(GameCharacter character : closeRegions[i].getCharacters().values()) {
				visibleCharacters.add(character);
			}
		}

		return visibleCharacters;
	}
	
	/**
	 * Adds a region to closeRegions array
	 * @param region
	 */
	public void addCloseRegion(Region region) {
		Region temp [] = new Region[closeRegions.length+1];
		
		for(int i = 0; i < closeRegions.length; i++) {
			temp[i] = closeRegions[i];
		}
		
		temp[temp.length-1] = region;
		closeRegions = temp;
	}
	
	public FastMap<Integer, GameCharacter> getCharacters() {
		return characters;
	}
}
