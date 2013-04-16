package com.theboxbrigade.quantumchaos.controllers;

import com.theboxbrigade.quantumchaos.Obstructing;
import com.theboxbrigade.quantumchaos.Position;
import com.theboxbrigade.quantumchaos.Tile;
import com.theboxbrigade.quantumchaos.TileManager;
import com.theboxbrigade.quantumchaos.general.AnimatedAssets;
import com.theboxbrigade.quantumchaos.general.Globals;
import com.theboxbrigade.quantumchaos.models.CatModel;
import com.theboxbrigade.quantumchaos.views.CatView;

public class CatController extends ObjectController implements Interactable, Obstructing {
	public static final int IDLE = 0;
	public static final int TELEPORT_IN = 1;
	public static final int TELEPORT_OUT = 2;
	
	private Position position;
	protected int facingDir = Globals.SOUTH;
	protected float x;
	protected float y;
	public int state = IDLE;
	protected int deltaState = 0;
	protected int stateFrame = 0;
	protected boolean moving;
	protected boolean visible = true;
	private TileManager tileManager;
	
	public CatController(TileManager tileManager) {
		this.tileManager = tileManager;
		position = new Position(this.tileManager);
		
		model = new CatModel(this);
		view = new CatView();
	}
	
	public void setPosition(Tile tile) {
		tileManager.getTile(position.getX(), position.getY()).setObstructed(false);
		tileManager.getTile(position.getX(), position.getY()).setObstructing(null);
		position.setTile(tile);
		tileManager.getTile(position.getX(), position.getY()).setObstructed(true);
		tileManager.getTile(position.getX(), position.getY()).setObstructing(this);
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setFacingDirection(int direction) {
		this.facingDir = direction;
	}
	
	public int facingDirection() {
		return facingDir;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() { 
		return visible;
	}
	
	public void resetState() { stateFrame = 0; }
	
	public void setScreenPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public void processInput(int input) {
		switch (input) {
			case TELEPORT_IN: 	((CatModel)model).teleportIn();
								break;
			case TELEPORT_OUT:	((CatModel)model).teleportOut();
								break;
		}
	}
	
	@Override
	public void whenInteractedWith() {
	}
	
	@Override
	public boolean update(float delta) {
		if (++deltaState > 2 && state != IDLE) {
			stateFrame++;
			deltaState = 0;
		}
		if (state == TELEPORT_IN && stateFrame >= AnimatedAssets.numTeleportingFrames) {
			state = IDLE;
			setVisible(true);
			resetState();
		} else if (state == TELEPORT_OUT && stateFrame >= AnimatedAssets.numTeleportingFrames) {
			state = IDLE;
			setVisible(false);
			resetState();
		}
		updateView();
		return false;
	}

	@Override
	protected void updateView() {
		if (visible) ((CatView)view).update(state, facingDir, stateFrame, x, y);
	}

	@Override
	public boolean equals(ObjectController other) {
		return false;
	}

	@Override
	public boolean isInteractable() {
		return true;
	}

	@Override
	public int interactableType() {
		return Interactable.CAT;
	}
}
