package com.friendlyblob.islandlife.client.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.friendlyblob.islandlife.client.MyGame;
import com.friendlyblob.islandlife.client.helpers.Assets;

public class LoadingScreen extends BaseScreen{

	private Texture px;
	
	public LoadingScreen(MyGame game) {
		super(game);
		Assets.manager.load("textures/debugging/px.png", Texture.class);
	}

	@Override
	public void draw(float deltaTime) {
		if(Assets.manager.update()){
			if(game.screenGame == null)
				game.screenGame = new GameScreen(game);
			game.setScreen(game.screenGame);
		}
		
		// Between 0 and 1
		float progress = Assets.manager.getProgress();
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public void prepare() {
		
	}

}
