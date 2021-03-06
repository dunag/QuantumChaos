package com.theboxbrigade.quantumchaos.general;

import com.theboxbrigade.quantumchaos.Tile;
import com.theboxbrigade.quantumchaos.controllers.Interactable;

public class InteractionManager {
	protected Tile playerTile;
	protected Tile interactingTile;
	protected Interactable interactor;
	
	public void setInteractor(Interactable i) {
		this.interactor = i;
	}
	
	public void setPlayerTile(Tile t) {
		this.playerTile = t;
	}
	
	public void setInteractingTile(Tile t) {
		this.interactingTile = t;
	}
	
	public void interact() {
		if (interactor != null && playerTile != null && interactingTile != null) {
			interactor.whenInteractedWith();
		}
	}
	
	public void interact(Tile playerTile, Tile interactingTile, Interactable interactor) {
		setPlayerTile(playerTile);
		setInteractingTile(interactingTile);
		setInteractor(interactor);
		interact();
	}
}