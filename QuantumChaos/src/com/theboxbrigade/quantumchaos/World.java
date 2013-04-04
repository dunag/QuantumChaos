package com.theboxbrigade.quantumchaos;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.theboxbrigade.quantumchaos.general.Input;

public abstract class World {
	protected SpriteBatch spriteBatch;
	protected OrthographicCamera camera;
	
	protected TiledMap tileMap;
	protected TextureAtlas tileAtlas;
	public IsometricTiledMapRenderer tileMapRenderer;
	protected AssetManager assetManager;
	
	public boolean readyToLeave = false;
	public int nextWorld = -1;
	
	public World() {
		create();
	}
	
	public abstract void create();
	
	public abstract void render(float delta);
	
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public IsometricTiledMapRenderer getMapRenderer() {
		return tileMapRenderer;
	}
	
	public abstract void parseInput(Input input);
	
	public int nextWorld() { return nextWorld; }
	
	public abstract void dispose();
}
