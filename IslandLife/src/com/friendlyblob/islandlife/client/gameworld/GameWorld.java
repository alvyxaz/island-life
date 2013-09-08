package com.friendlyblob.islandlife.client.gameworld;

import javolution.util.FastMap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.entities.GameCharacter;
import com.friendlyblob.islandlife.client.entities.Player;
import com.friendlyblob.islandlife.client.mapeditor.MapEditor;

public class GameWorld {
	public static GameWorld instance;
	
	/*-------------------------------------
	 * Entities
	 */
	private Map map;
	public Player player;
	
	public FastMap<Integer,GameCharacter> characters = new FastMap<Integer,GameCharacter>().shared();
	
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
	
	public void putCharacter(GameCharacter character) {
		characters.put(character.objectId, character);
	}
	
	public void removeCharacter(int id) {
		characters.remove(id);
	}
	
	public boolean characterExists(int id) {
		return characters.containsKey(id);
	}
	
	public GameCharacter getCharacter(int id) {
		return characters.get(id);
	}
	
	public void update(float deltaTime) {
		player.update(deltaTime);
		
		// TODO optimize to avoid iterators (Make sure FastMap uses them first)
		for (GameCharacter character : characters.values()) {
			character.update(deltaTime);
		}
		
		map.update(deltaTime);
		
		if (!MapEditor.enabled){
			cameraFollowPlayer(deltaTime);
		}
	}
	
	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.setProjectionMatrix(worldCam.combined);
		map.draw(spriteBatch);
		player.draw(spriteBatch);
		
		// TODO optimize to avoid iterators (Make sure FastMap uses them first)
		for (GameCharacter character : characters.values()) {
			character.draw(spriteBatch);
		}
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
	
	public static void initialize() {
		instance = new GameWorld();
	}
	
	public static GameWorld getInstance() {
		return instance;
	}
	
}
