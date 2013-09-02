package com.friendlyblob.islandlife.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.controls.Input;
import com.friendlyblob.islandlife.client.entities.Player;
import com.friendlyblob.islandlife.client.helpers.Assets;
import com.friendlyblob.islandlife.client.map.Map;
import com.friendlyblob.islandlife.client.mapeditor.MapEditor;

public class GameScreen extends BaseScreen{

	/*-------------------------------------
	 * Camera
	 */
	private OrthographicCamera worldCam;
	
	/*-------------------------------------
	 * Entities
	 */
	private Map map;
	private Player player;
	
	public GameScreen(MyGame game) {
		super(game);
		
		/*--------------------------------
		 * World camera setup
		 */
		worldCam = new OrthographicCamera(MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT);
		worldCam.translate(MyGame.SCREEN_WIDTH/2, MyGame.SCREEN_HEIGHT/2);
		worldCam.update();
		
		/*
		 * Entities initialization
		 */
		map = Map.getInstance();
		map.load(worldCam);
		
		player = new Player(50, 50);
	}

	@Override
	public void draw(float deltaTime) {
		spriteBatch.begin();
		/*---------------------------------------
		 * World
		 */
		spriteBatch.setProjectionMatrix(worldCam.combined);
		
		map.draw(spriteBatch);
		
		player.draw(spriteBatch);
		
		/*---------------------------------------
		 * GUI Elements
		 */
		spriteBatch.setProjectionMatrix(guiCam.combined);
		
		if (MapEditor.enabled){
			MapEditor.drawInfo(spriteBatch);
		}
		
		Assets.defaultFont.draw(spriteBatch, fpsText, 50, 50);
		
		spriteBatch.end();
	}
	
	@Override
	public void update(float deltaTime) {
		map.update(deltaTime);
		
		if (!MapEditor.enabled) {
			updateGameplayInput();
		}
	}
	
	/*
	 * Analysing user input in gameplay mode.
	 */
	public void updateGameplayInput() {
		if (Input.isReleasing()) {
			player.moveTo(map.toWorldX(Input.getX()), map.toWorldY(Input.getY()));
		}
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

}
