package com.friendlyblob.islandlife.client.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.controls.Input;
import com.friendlyblob.islandlife.client.helpers.Assets;
import com.friendlyblob.islandlife.client.mapeditor.MapEditor;

public class Map {
	
	private Texture texture;
	private TextureRegion [] tileTextures;
	
	private int [][] tiles;
	
	public static final int TILE_WIDTH = 96;
	public static final int TILE_HEIGHT = 48;
	
	private OrthographicCamera worldCam;
	private Vector3 camPos;
	
	private static Vector2 tileTarget = new Vector2(); 
	
	public Map() {
		/*
		 * Loading main assets. 
		 * TODO Either load it at loading screen or load() method to be loaded for each level separately 
		 */
		Assets.manager.load("textures/tiles/normal.png", Texture.class);
		Assets.manager.finishLoading();
		texture = Assets.manager.get("textures/tiles/normal.png", Texture.class);
		
		/*
		 * Loading all textureRegions
		 */
		int xTextureCount = texture.getWidth()/TILE_WIDTH;
		int yTextureCount = texture.getHeight()/TILE_HEIGHT;
		
		tileTextures = new TextureRegion[xTextureCount*yTextureCount];
		
		for (int i = 0; i < tileTextures.length; i++){
			tileTextures[i] = new TextureRegion(texture, (i%xTextureCount)*96, (i/yTextureCount)*48, 96, 48);
		}
		
	}
	
	public void draw(SpriteBatch spriteBatch) {
		
		// Calculating bounds
		int startX = Math.max((int)((camPos.x - MyGame.SCREEN_HALF_WIDTH)/TILE_WIDTH)-1, 0);
		int startY = Math.max((int)((camPos.y - MyGame.SCREEN_HALF_HEIGHT)/(TILE_HEIGHT/2)-1), 0);
		int endX = Math.min(startX + MyGame.SCREEN_WIDTH/TILE_WIDTH+3, tiles[0].length);
		int endY = Math.min(startY + MyGame.SCREEN_HEIGHT/(TILE_HEIGHT/2)+2, tiles.length);
		
		for (int y = startY; y < endY; y++){
			for(int x = startX; x < endX; x++){
				spriteBatch.draw(tileTextures[tiles[y][x]], x*TILE_WIDTH + y%2 * TILE_WIDTH/2, y * TILE_HEIGHT/2);
			}
		}
	
	}
	
	public void update(float deltaTime) {
		updateTileTarget();
		
		if (Gdx.input.isTouched()) {
			System.out.println(tileTarget.y);
			tiles[(int)tileTarget.y][(int)tileTarget.x] = MapEditor.tileTextureSelected;
		}
	}
	
	public void updateTileTarget() {
		int worldX = (int)(Input.getX()*worldCam.zoom + camPos.x-MyGame.SCREEN_HALF_WIDTH);
		int worldY = (int)(Input.getY()*worldCam.zoom + camPos.y-MyGame.SCREEN_HALF_HEIGHT);
		
		// Preliminary values
		tileTarget.x = ((worldY / (TILE_HEIGHT/2))%2 * TILE_WIDTH/2 - worldX)/-TILE_WIDTH;
		tileTarget.y = worldY/(TILE_HEIGHT/2);
		
		// Offsets from middle bottom of a preliminary tile
		int diffX = worldX - (int)tileTarget.x*TILE_WIDTH - (TILE_WIDTH/2) * ((int)tileTarget.y %2) - TILE_WIDTH/2;
		int diffY = worldY - (int)tileTarget.y *(TILE_HEIGHT/2);
		
		if (diffY*2 < Math.abs(diffX)){
			if(diffX < 0){
				tileTarget.x += (tileTarget.y%2) == 0? -1 : 0;
			} else {
				tileTarget.x += (tileTarget.y%2) == 0? 0 : 1;
			}
			tileTarget.y--;
		}
	}

	public void load(OrthographicCamera worldCam) {
		this.worldCam = worldCam;
		camPos = worldCam.position;
		
		tiles = new int[100][50];

	}
	
	public static Map getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static class SingletonHolder {
		public static final Map INSTANCE = new Map();
	}
	
}
