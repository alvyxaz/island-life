package com.friendlyblob.islandlife.server.network.packets.client;

import com.friendlyblob.islandlife.server.DatabaseFactory;
import com.friendlyblob.islandlife.server.network.GameClientPacket;

public class LoginPacket extends GameClientPacket{

	private String login;
	private String password;
	
	@Override
	protected boolean read() {
		login = readS();
		password = readS();
		return true;
	}

	@Override
	public void run() {
		System.out.println("Received login info: " + login + " " + password);
	}

}
