package com.friendlyblob.islandlife.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.helpers.Assets;
import com.friendlyblob.islandlife.client.map.Map;

public class GameScreen extends BaseScreen{

	/*-------------------------------------
	 * Camera
	 */
	private OrthographicCamera worldCam;
	
	/*-------------------------------------
	 * Entities
	 */
	private Map map;
	
	public GameScreen(MyGame game) {
		super(game);
		
		/*--------------------------------
		 * World camera setup
		 */
		worldCam = new OrthographicCamera(MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT);
		worldCam.translate(MyGame.SCREEN_WIDTH/2, MyGame.SCREEN_HEIGHT/2);
		worldCam.update();
		
		map = Map.getInstance();
		map.load(worldCam);
	}

	@Override
	public void draw(float deltaTime) {
		spriteBatch.begin();
		/*---------------------------------------
		 * World
		 */
		spriteBatch.setProjectionMatrix(worldCam.combined);
		
		map.draw(spriteBatch);
		
		/*---------------------------------------
		 * GUI Elements
		 */
		spriteBatch.setProjectionMatrix(guiCam.combined);
		
		Assets.defaultFont.draw(spriteBatch, fpsText, 50, 50);
		
		spriteBatch.end();
	}
	
	@Override
	public void update(float deltaTime) {
		
		map.update(deltaTime);
		
		// Camera controls
		final int cameraSpeed = 1000;
		if(Gdx.input.isKeyPressed(Keys.A)){
			worldCam.position.x -= cameraSpeed * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			worldCam.position.x += cameraSpeed * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Keys.W)){
			worldCam.position.y += cameraSpeed * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			worldCam.position.y -= cameraSpeed * deltaTime;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			worldCam.zoom -= deltaTime*2;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			worldCam.zoom += deltaTime*2;
		}
		worldCam.update();
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

}
