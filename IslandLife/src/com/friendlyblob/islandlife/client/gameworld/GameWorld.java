package com.friendlyblob.islandlife.client.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.entities.MovableObject;
import com.friendlyblob.islandlife.client.entities.Player;

public class GameWorld {
	/*-------------------------------------
	 * Entities
	 */
	private Map map;
	public Player player;
	
	private MovableObject [] characters;
	
	/*-------------------------------------
	 * Camera
	 */
	private OrthographicCamera worldCam;

	public GameWorld() {
		
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
		map.setWorld(this);
		map.load(worldCam);

		player = new Player(50, 50);
	}
	
	public void update(float deltaTime) {
		player.update(deltaTime);
		map.update(deltaTime);
		
		cameraFollowPlayer(deltaTime);
	}
	
	public void draw(SpriteBatch sb) {
		sb.setProjectionMatrix(worldCam.combined);
		map.draw(sb);
		player.draw(sb);
	}
	
	public void cameraFollowPlayer(float deltaTime){
		// X follow
		if (player.hitBox.x > worldCam.position.x + MyGame.SCREEN_WIDTH*0.15f) {
			worldCam.position.x += player.hitBox.x - (worldCam.position.x + MyGame.SCREEN_WIDTH*0.15f);
		} else if (player.hitBox.x < worldCam.position.x - MyGame.SCREEN_WIDTH*0.15f) {
			worldCam.position.x -= (worldCam.position.x - MyGame.SCREEN_WIDTH*0.15f) - player.hitBox.x;
		}
		
		// Y follow
		if (player.hitBox.y > worldCam.position.y + MyGame.SCREEN_HEIGHT*0.15f) {
			worldCam.position.y += player.hitBox.y - (worldCam.position.y + MyGame.SCREEN_HEIGHT*0.15f);
		} else if (player.hitBox.y < worldCam.position.y - MyGame.SCREEN_HEIGHT*0.15f) {
			worldCam.position.y -= (worldCam.position.y - MyGame.SCREEN_HEIGHT*0.15f) - player.hitBox.y;
		}
		
		worldCam.update();
	}
	
	public Map getMap() {
		return map;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/*
	* Translate screen x coordinates to world x coordinate
	*/
	public int toWorldX(int x) {
		return (int)(x*worldCam.zoom + map.getCamPos().x-MyGame.SCREEN_HALF_WIDTH);
	}
	
	/*
	* Translate screen y coordinates to world y coordinate
	*/
	public int toWorldY(int y) {
		return (int)(y*worldCam.zoom + map.getCamPos().y-MyGame.SCREEN_HALF_HEIGHT);
	}
}
