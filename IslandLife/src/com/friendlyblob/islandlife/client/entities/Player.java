package com.friendlyblob.islandlife.client.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Player {

	// Physics
	public Body body;
	
	private int width;
	private int height;
	
	private TextureRegion texture;
	
	private int xVelocity = 100;
	

	public Player (int x, int y, World world){

	}

	public void regenerate(int x, int y){

	}
	
	public void update(float deltaTime){

	}
	
	public void draw(SpriteBatch spriteBatch){
	}
	
}
