package com.friendlyblob.islandlife.client.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.friendlyblob.islandlife.client.MyGame;

public class GameScreen extends BaseScreen{

	/*-------------------------------------
	 * Camera
	 */
	private OrthographicCamera worldCam;
	
	/*-------------------------------------
	 * Entities
	 */
	
	public GameScreen(MyGame game) {
		super(game);
		
		/*--------------------------------
		 * World camera setup
		 */
		worldCam = new OrthographicCamera(MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT);
		worldCam.translate(MyGame.SCREEN_WIDTH/2, MyGame.SCREEN_HEIGHT/2);
		worldCam.update();
				
	}

	@Override
	public void draw(float deltaTime) {
		spriteBatch.begin();
		
		/*---------------------------------------
		 * World
		 */
		spriteBatch.setProjectionMatrix(worldCam.combined);
		
		/*---------------------------------------
		 * GUI Elements
		 */
		spriteBatch.setProjectionMatrix(guiCam.combined);
		
		spriteBatch.end();
	}
	
	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	}

}
