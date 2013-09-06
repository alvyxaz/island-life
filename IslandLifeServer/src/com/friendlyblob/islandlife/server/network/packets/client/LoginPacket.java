package com.friendlyblob.islandlife.server.network.packets.client;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.friendlyblob.islandlife.server.DatabaseFactory;
import com.friendlyblob.islandlife.server.model.World;
import com.friendlyblob.islandlife.server.model.actors.Player;
import com.friendlyblob.islandlife.server.network.GameClient;
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
		// check login
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] hashDigest = null;
		try {
			hashDigest = md.digest(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String hash = "";
		for (int i=0; i < hashDigest.length; i++) {
			hash +=
		        Integer.toString( ( hashDigest[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		
		try (Connection con = DatabaseFactory.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?"))
		{
			ps.setString(1, login);
			ps.setString(2, hash);
			ResultSet rset = ps.executeQuery();
			
			 while (rset.next()) {
				 if (rset.getInt(1) > 0) {
					 getClient().setState(GameClient.GameClientState.IN_GAME);
					 // TODO fetch player data from database and attach Player object to connection
					 Player player = new Player();
					 getClient().setPlayer(player);
					 player.setClient(getClient());
					 World.getInstance().addPlayer(player);
					 break;
				 }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
