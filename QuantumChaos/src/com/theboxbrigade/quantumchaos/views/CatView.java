package com.theboxbrigade.quantumchaos.views;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.theboxbrigade.quantumchaos.controllers.CatController;
import com.theboxbrigade.quantumchaos.general.AnimatedAssets;
import com.theboxbrigade.quantumchaos.general.Assets;
import com.theboxbrigade.quantumchaos.general.Globals;

public class CatView extends CharacterView {
	private Sprite cat = Assets.catS;
	private SpriteBatch spriteBatch = new SpriteBatch();
	
	@Override
	public void update(int state) {
		update(state, Globals.NORTH, 0, 0, 0);
	}
	
	public void update(int state, int facingDir, int frame, float x, float y) {
		switch (state) {
			case CatController.IDLE:			cat = Assets.catS;
												break;
			case CatController.TELEPORT_IN:		cat = AnimatedAssets.getCatFrame(state, facingDir, frame);
												break;
			case CatController.TELEPORT_OUT:	cat = AnimatedAssets.getCatFrame(state, facingDir, AnimatedAssets.numTeleportingFrames - frame - 1);
												break;
		}
		draw(cat, x, y);
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	@Override
	public void draw(Sprite region, float x, float y) {
		if (region != null) spriteBatch.draw(region, x, y);
	}
}
