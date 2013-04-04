package com.theboxbrigade.quantumchaos.models;

import com.theboxbrigade.quantumchaos.controllers.PlanetController;

public class PlanetModel extends Model {
	protected int state;
	
	public PlanetModel(PlanetController controller) {
		this.controller = controller;
	}
	
	public void placeOnPedestal() {
		state = PlanetController.ON_PEDESTAL;
		// TODO
	}
	
	public void pickUp() {
		state = PlanetController.IN_HANDS;
		// TODO
	}
	
	public void drop() {
		state = PlanetController.ON_FLOOR;
		// TODO
	}
	
	@Override
	protected void sync() {
		((PlanetController)controller).setState(state);
	}
	
}
