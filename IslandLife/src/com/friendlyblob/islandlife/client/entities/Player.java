package com.friendlyblob.islandlife.client.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.friendlyblob.islandlife.client.helpers.Assets;

public class Player {

	// Physics
	public Body body;
	
	private int width;
	private int height;
	
	private int xVelocity = 100;
	
	public Rectangle hitBox;
	
	private int targetX;
	private int targetY;

	public Player (int x, int y){
		hitBox = new Rectangle(x, y, 32, 64);
	}

	public void regenerate(int x, int y){

	}
	
	public void update(float deltaTime){

	}
	
	public void draw(SpriteBatch spriteBatch){
		spriteBatch.setColor(Color.CYAN);
		spriteBatch.draw(Assets.px, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		spriteBatch.setColor(Color.WHITE);
	}
	
	public void moveTo (int x, int y) {
		
	}
	
}
