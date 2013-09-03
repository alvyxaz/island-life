package com.friendlyblob.islandlife.client.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	
	private static int [][] tiles;
	
	public static final int TILE_WIDTH = 96;
	public static final int TILE_HEIGHT = 48;
	
	private OrthographicCamera worldCam;
	private Vector3 camPos;
	
	private static Vector2 tileTarget = new Vector2(); 
	
	private GameWorld world;
	
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
				if (y < tiles.length && y >= 0 && x < tiles[y].length && x >= 0)
					spriteBatch.draw(tileTextures[tiles[y][x]], x*TILE_WIDTH + y%2 * TILE_WIDTH/2, y * TILE_HEIGHT/2);
			}
		}
	}
	
	public void update(float deltaTime) {
		if (Gdx.input.isTouched()) {
			updateTileTarget(); // Target is not MapEditor specific
		}	
		
		// Toggle map editor with F1 key
		if (Input.keyReleased(Keys.F1)){
			MapEditor.toggle();
		}
		
		// Map editor related updates
		if (MapEditor.enabled){
			MapEditor.cameraUpdate(worldCam, deltaTime);
			if (Gdx.input.isTouched()) {
				tiles[(int)tileTarget.y][(int)tileTarget.x] = MapEditor.tileTextureSelected;
			}
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
		
		tileTarget.x = Math.max(Math.min(tileTarget.x, tiles[0].length-1), 0);
		tileTarget.y = Math.max(Math.min(tileTarget.y, tiles.length-1), 0);
	}
	
	public void setWorld(GameWorld gameWorld) {
		world = gameWorld;
	}
	
	public Vector3 getCamPos() {
		return camPos;
	}
	
	public void load(OrthographicCamera worldCam) {
		this.worldCam = worldCam;
		camPos = worldCam.position;
		
		tiles = new int[100][50];

	}
	
	public static void load(int[][] map) {
		tiles = map;
	}
	
	public static int[][] getTiles() {
		return tiles;
	}
	
	public static Map getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static class SingletonHolder {
		public static final Map INSTANCE = new Map();
	}
}
