package com.theboxbrigade.quantumchaos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Input implements InputProcessor {
	public static final int WALK_NORTH = 0;
	public static final int WALK_EAST = 1;
	public static final int WALK_SOUTH = 2;
	public static final int WALK_WEST = 3;
	
	public static final int INTERACT = 4;
	public static final int ATTACK = 5;
	
	public static final int ESCAPE = 6;
	
	public static final int DPAD_UP = 7;
	public static final int DPAD_DOWN = 8;
	
	public boolean[] buttons = new boolean[64];
	public boolean[] oldButtons = new boolean[64];
	
	public void set(int key, boolean down) {
		int button = -1;
		
		if (key == Keys.W) button = WALK_NORTH;
		if (key == Keys.D) button = WALK_EAST;
		if (key == Keys.S) button = WALK_SOUTH;
		if (key == Keys.A) button = WALK_WEST;
		
		if (key == Keys.E || key == Keys.ENTER) button = INTERACT;
		if (key == Keys.SPACE) button = ATTACK;
		if (key == Keys.ESCAPE) button = ESCAPE;
		
		if (key == Keys.DPAD_UP) button = DPAD_UP;
		if (key == Keys.DPAD_DOWN) button = DPAD_DOWN;
		
		if (button >= 0) buttons[button] = down;
	}
	
	public void tick() {
		for (int i=0; i<buttons.length; i++) {
			oldButtons[i] = buttons[i];
		}
	}
	
	public void releaseAllKeys() {
		for (int i=0; i<buttons.length; i++) {
			if (i == DPAD_UP || i == DPAD_DOWN) continue;
			buttons[i] = false;
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) { return false; }
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) { return false; }
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) { return false; }
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) { return false; }
	
	@Override
	public boolean mouseMoved(int x, int y) { return false; }
	
	@Override
	public boolean scrolled(int amount) { return false; }
}
