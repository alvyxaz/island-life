package com.friendlyblob.islandlife.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.controls.Input;
import com.friendlyblob.islandlife.client.entities.Player;
import com.friendlyblob.islandlife.client.gameworld.GameWorld;
import com.friendlyblob.islandlife.client.gameworld.Map;
import com.friendlyblob.islandlife.client.helpers.Assets;
import com.friendlyblob.islandlife.client.mapeditor.MapEditor;

public class GameScreen extends BaseScreen{
	private GameWorld world;
	public GameScreen(MyGame game) {
		super(game);
		
		GameWorld.initialize();
		world = GameWorld.getInstance();
		world.setGame(game);
		game.connectToServer();
	}

	@Override
	public void draw(float deltaTime) {
		spriteBatch.begin();
		/*---------------------------------------
		 * World
		 */
		world.draw(spriteBatch);
		
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
		world.update(deltaTime);
		
		if (!MapEditor.enabled) {
			updateGameplayInput();
		}
	}
	
	/*
	 * Analysing user input in gameplay mode.
	 */
	public void updateGameplayInput() {
		if (Input.isReleasing()) {
			world.getPlayer().requestMovementDestination(world.toWorldX(Input.getX()), world.toWorldY(Input.getY()));
		}
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}
	
	public GameWorld getWorld() {
		return world;
	}

}
