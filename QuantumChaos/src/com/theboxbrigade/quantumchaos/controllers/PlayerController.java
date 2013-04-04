package com.theboxbrigade.quantumchaos.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theboxbrigade.quantumchaos.Carryable;
import com.theboxbrigade.quantumchaos.Obstructing;
import com.theboxbrigade.quantumchaos.Position;
import com.theboxbrigade.quantumchaos.Tile;
import com.theboxbrigade.quantumchaos.TileManager;
import com.theboxbrigade.quantumchaos.general.AnimatedAssets;
import com.theboxbrigade.quantumchaos.general.Globals;
import com.theboxbrigade.quantumchaos.general.Input;
import com.theboxbrigade.quantumchaos.models.Model;
import com.theboxbrigade.quantumchaos.models.PlayerModel;
import com.theboxbrigade.quantumchaos.views.PlayerView;
import com.theboxbrigade.quantumchaos.views.View;

/**
 * Create this object in a World to get the MVC structure for a Player object (Robert)
 * <br />
 * Also creates PlayerModel, PlayerView objects
 * @author Jason
 */
public class PlayerController extends ObjectController implements Obstructing {
	public static final int IDLE = 10;
	public static final int WALKING = 11;
	public static final int INTERACTING = 12;
	public static final int TELEPORTING = 13;
	public static final int ATTACKING = 14;
	public static final int HIT = 15;
	public static final int DEAD = 16;
	public static final int SLIDING = 17;
	
	protected int maxHP = 4;
	protected int currentHP = 4;
	protected Position position;
	public int state = IDLE;
	protected int deltaState = 0;
	protected int stateFrame = 0;
	protected Interactable interactingWith;
	protected int facing = Globals.NORTH;
	protected boolean moved = false;
	protected Carryable itemCarried;
	protected boolean carrying = false;
	
	protected TileManager tileManager;
	
	public PlayerController(TileManager tileManager) {
		this.tileManager = tileManager;
		position = new Position(this.tileManager);
		
		model = new PlayerModel(this);
		view = new PlayerView();
	}
	
	public void setHP(int hp) { this.currentHP = hp; }
	public int getHP() { return currentHP; }
	
	public void setPosition(Position p) {
		position.setTile(p.getTile());
	}
	public Position getPosition() { return position; }
	
	/** Looks forward one Tile in the direction Robert is facing and returns that tile
	 * @return Tile: The tile in front of Robert (null if he's at an edge of the tile map)
	 */
	public Tile getTileInFrontOfPlayer() {
		switch (facing) {
			case Globals.NORTH: return tileManager.getTile(position.getX(), position.getY()-1);
			case Globals.EAST: 	return tileManager.getTile(position.getX()+1, position.getY());
			case Globals.SOUTH: return tileManager.getTile(position.getX(), position.getY()+1);
			case Globals.WEST: 	return tileManager.getTile(position.getX()-1, position.getY());
		}
		return null;
	}
	
	public void setState(int state) { this.state = state; }
	public int getStateFrame() { return stateFrame; }
	public void resetState() { stateFrame = 0; }
	public void setFacing(int facing) { this.facing = facing; }
	public int getFacing() { return facing; }
	public void setInteractable(Interactable interactingWith) { this.interactingWith = interactingWith; }
	public Interactable getInteractable() { return interactingWith; }
	public void setCarrying(boolean carrying) { this.carrying = carrying; }
	public boolean isCarrying() { return carrying; }
	
	@Override
	public void processInput(int keyPressed) {
		switch (keyPressed) {
			case Input.WALK_NORTH: 	if (state != WALKING) moved = ((PlayerModel)model).move(Globals.NORTH);
									break;
			case Input.WALK_EAST:	if (state != WALKING) moved = ((PlayerModel)model).move(Globals.EAST);
									break;
			case Input.WALK_SOUTH:	if (state != WALKING) moved = ((PlayerModel)model).move(Globals.SOUTH);
									break;
			case Input.WALK_WEST:	if (state != WALKING) moved = ((PlayerModel)model).move(Globals.WEST);
									break;
			case Input.INTERACT:	((PlayerModel)model).interactWith(interactingWith);
									break;
		}
		System.out.println("State: " + state);
		System.out.println(position);
	}
	
	/**
	 * @return advanceAnim: 'true' if advancing to the next animation frame
	 * If this returns true, the frame advanced on this call.
	 */
	@Override
	public boolean update(float delta) {
		boolean advanceAnim = false;
		if (++deltaState > 2 && moved) {
			stateFrame++;
			deltaState = 0;
			advanceAnim = true;
		} else if (!moved) state = IDLE;
		if (state == WALKING && stateFrame >= AnimatedAssets.numWalkingFrames) {
			state = IDLE;
			resetState();
		}
		updateView();
		return advanceAnim;
		//return true;
	}
	
	@Override
	protected void updateView() {
		((PlayerView)view).update(state, facing, stateFrame);
	}
	
	@Override
	public boolean equals(ObjectController other) {
		return false;
	}

	@Override
	public void translate(float x, float y) {
	}
}
