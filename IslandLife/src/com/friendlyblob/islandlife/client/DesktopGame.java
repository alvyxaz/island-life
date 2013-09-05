package com.friendlyblob.islandlife.client;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import com.friendlyblob.islandlife.client.network.Connection;
import com.friendlyblob.islandlife.client.network.PacketHandler;
import com.friendlyblob.islandlife.client.network.packets.DummyPacket;
import com.friendlyblob.islandlife.client.network.packets.client.ClientVersion;

public class DesktopGame {
	public static void main (String[] args) {
		// TODO: Remove tools dependency
		Settings settings = new Settings();
		TexturePacker2.process(settings, "textures", "../IslandLifeAndroid/assets/textures", "textures");
		
		ActionResolver actionResolver = new ActionResolverDesktop();
		MyGame game = new MyGame(new GoogleDesktop(), actionResolver);
		game.ads = new AdsDesktop();
		
		try {
			MyGame.connection = new Connection(new PacketHandler(), "localhost", 7777);
			MyGame.connection.game = game; // TODO think of something smarter
			MyGame.connection.start();
			MyGame.connection.sendPacket(new ClientVersion(5));
		} catch (Exception e){
			System.out.println();
		}
		
		((GoogleDesktop)game.google).game = game;
        new LwjglApplication(game, "Game", (int) 800, 480, false);
	}
}
