package com.theboxbrigade.quantumchaos.models;

import com.theboxbrigade.quantumchaos.controllers.CatController;

public class CatModel extends CharacterModel {
	protected int state;
	protected int facingDir;
	protected boolean moving;
	protected boolean talking;
	
	public CatModel(CatController controller) {
		this.controller = controller;
	}
	
	public void returnToIdle() {
		state = CatController.IDLE;
		sync();
	}
	
	@Override
	public void face(int direction) {
		facingDir = direction;
		sync();
	}
	
	@Override
	public boolean move(int direction) {
		face(direction);
		return false;
	}
	
	public void teleportIn() {
		state = CatController.TELEPORT_IN;
		((CatController)controller).setVisible(true);
		sync();
	}
	
	public void teleportOut() {
		state = CatController.TELEPORT_OUT;
		((CatController)controller).setVisible(true);
		sync();
	}

	@Override
	protected void sync() {
		((CatController)controller).setFacingDirection(facingDir);
		((CatController)controller).setState(state);
	}
}
