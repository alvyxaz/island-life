package com.friendlyblob.islandlife.client.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.controls.Input;
import com.friendlyblob.islandlife.client.helpers.Assets;

public class Map {
	
	private Texture texture;
	private TextureRegion [] tileTextures;
	
	private int [][] tiles;
	
	public static final int TILE_WIDTH = 96;
	public static final int TILE_HEIGHT = 48;
	
	private OrthographicCamera worldCam;
	private Vector3 camPos;
	
	public Map() {
		Assets.manager.load("textures/tiles/normal.png", Texture.class);
		Assets.manager.finishLoading();
		texture = Assets.manager.get("textures/tiles/normal.png", Texture.class);
		
		tileTextures = new TextureRegion[2];
		tileTextures[0] = new TextureRegion(texture, 0, 0, 96, 48);
		tileTextures[1] = new TextureRegion(texture, 96, 0, 96, 48);
	}
	
	public void draw(SpriteBatch spriteBatch) {
		
		int startX = Math.max((int)((camPos.x - MyGame.SCREEN_HALF_WIDTH)/TILE_WIDTH)-1, 0);
		int startY = Math.max((int)((camPos.y - MyGame.SCREEN_HALF_HEIGHT)/(TILE_HEIGHT/2)-1), 0);
		int endX = Math.min(startX + MyGame.SCREEN_WIDTH/TILE_WIDTH+3, tiles[0].length);
		int endY = Math.min(startY + MyGame.SCREEN_HEIGHT/(TILE_HEIGHT/2)+2, tiles.length);
		
		for (int y = startY; y < endY; y++){
			for(int x = startX; x < endX; x++){
				spriteBatch.draw(tileTextures[0], x*TILE_WIDTH + y%2 * TILE_WIDTH/2, y * TILE_HEIGHT/2);
				Assets.defaultFont.draw(spriteBatch, x+":"+y, x*TILE_WIDTH + y%2 * TILE_WIDTH/2 + 20,  y * TILE_HEIGHT/2 - 10 + TILE_HEIGHT );
			}
		}
		
		int mouseX = (int)(Input.getX()*worldCam.zoom + camPos.x-MyGame.SCREEN_HALF_WIDTH);
		int mouseY = (int)(Input.getY()*worldCam.zoom + camPos.y-MyGame.SCREEN_HALF_HEIGHT);
		
		int hoverX = getTileIndexX(mouseX, mouseY);
		int hoverY = getTileIndexY(mouseX, mouseY);
		
		spriteBatch.draw(tileTextures[1],hoverX*TILE_WIDTH + hoverY%2 * TILE_WIDTH/2, hoverY * TILE_HEIGHT/2);
	
	}

	public int getTileIndexX(int worldX, int worldY) {
		int preX = ((worldY / (TILE_HEIGHT/2))%2 * TILE_WIDTH/2 - worldX)/-TILE_WIDTH;
		int preY = worldY/(TILE_HEIGHT/2);

		int diffX = worldX - preX*TILE_WIDTH - (TILE_WIDTH/2) * (preY%2) - TILE_WIDTH/2;
		int diffY = worldY - preY*(TILE_HEIGHT/2);

		if (diffY*2 < Math.abs(diffX)){

			if(diffX < 0){
				preX += (preY%2) == 0? -1 : 0;
			} else {
				preX += (preY%2) == 0? 0 : 1;
			}
			preY--;
		}
		
		return preX;
	}
	
	public int getTileIndexY(int worldX, int worldY) {
		int preX = ((worldY / (TILE_HEIGHT/2))%2 * TILE_WIDTH/2 - worldX)/-TILE_WIDTH;
		int preY = worldY/(TILE_HEIGHT/2);

		int diffX = worldX - preX*TILE_WIDTH - (TILE_WIDTH/2) * (preY%2) - TILE_WIDTH/2;
		int diffY = worldY - preY*(TILE_HEIGHT/2);

		if (diffY*2 < Math.abs(diffX)){

			if(diffX < 0){
				preX += (preY%2) == 0? -1 : 0;
			} else {
				preX += (preY%2) == 0? 0 : 1;
			}
			preY--;
		}
		return preY;
	}
	
	public void load(OrthographicCamera worldCam) {
		this.worldCam = worldCam;
		camPos = worldCam.position;
		
		tiles = new int[100][50];
		for(int i = 0; i < tiles[0].length; i++){
			tiles[4][i] = 1;
		}
	}
	
	public static Map getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static class SingletonHolder {
		public static final Map INSTANCE = new Map();
	}
	
}
