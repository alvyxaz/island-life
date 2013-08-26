package com.friendlyblob.islandlife.client.helpers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	public static Assets assets;
	public static AssetManager manager;
	
	public static void initialize(){
		assets = new Assets();
		manager = new AssetManager();
	}
	
}
