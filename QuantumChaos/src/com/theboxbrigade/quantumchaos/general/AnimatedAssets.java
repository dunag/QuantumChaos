package com.theboxbrigade.quantumchaos.general;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.theboxbrigade.quantumchaos.controllers.CatController;
import com.theboxbrigade.quantumchaos.controllers.PlayerController;

public class AnimatedAssets {
	// ROBERT
	private static final int FRAME_WIDTH = 64;
	private static final int FRAME_HEIGHT = 128;
	private static final int PICKUP_Y_OFF = 512;
	private static final int CARRY_X_OFF = 320;
	public static int numWalkingFrames = 5;
	public static int numPickUpFrames = 3;
	private static final String robertPath = "data/images/robert_walk.png";
	private static Sprite[] robertWalkNorthFrames, robertWalkEastFrames, robertWalkSouthFrames, robertWalkWestFrames;
	private static Sprite[] pickUpNorthFrames, pickUpEastFrames, pickUpSouthFrames, pickUpWestFrames;
	private static Sprite[] carryNorthFrames, carryEastFrames, carrySouthFrames, carryWestFrames;
	public static int robertCurrentFrameNum = -1;
	public static Sprite robertCurrentFrame;
	
	// CAT
	public static int numTeleportingFrames = 8;
	private static final String catPath = "data/images/cat_anim.png";
	private static Sprite[] catTeleportFrames;
	public static Sprite catCurrentFrame;

	public static void load() {
		// ROBERT
		robertWalkNorthFrames = new Sprite[numWalkingFrames];
		robertWalkWestFrames = new Sprite[numWalkingFrames];
		robertWalkEastFrames = new Sprite[numWalkingFrames];
		robertWalkSouthFrames = new Sprite[numWalkingFrames];
		pickUpNorthFrames = new Sprite[numPickUpFrames];
		pickUpEastFrames = new Sprite[numPickUpFrames];
		pickUpSouthFrames = new Sprite[numPickUpFrames];
		pickUpWestFrames = new Sprite[numPickUpFrames];
		carryNorthFrames = new Sprite[numWalkingFrames];
		carryEastFrames = new Sprite[numWalkingFrames];
		carrySouthFrames = new Sprite[numWalkingFrames];
		carryWestFrames = new Sprite[numWalkingFrames];
		for (int i = 0; i < numWalkingFrames; i++) {
			robertWalkNorthFrames[i] = load(robertPath, i*FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT, false);
			robertWalkWestFrames[i] = load(robertPath, i*FRAME_WIDTH, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, false);
			robertWalkEastFrames[i] = load(robertPath, i*FRAME_WIDTH, 2*FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, false);
			robertWalkSouthFrames[i] = load(robertPath, i*FRAME_WIDTH, 3*FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, false);
			carryNorthFrames[i] = load(robertPath, CARRY_X_OFF + i*71, 0, 71, FRAME_HEIGHT, false);
			carryWestFrames[i] = load(robertPath, CARRY_X_OFF + i*71, FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
			carryEastFrames[i] = load(robertPath, CARRY_X_OFF + i*71, 2*FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
			carrySouthFrames[i] = load(robertPath, CARRY_X_OFF + i*71, 3*FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
		}
		for (int i=0; i < numPickUpFrames; i++) {
			pickUpNorthFrames[i] = load(robertPath, i*71, PICKUP_Y_OFF, 71, FRAME_HEIGHT, false);
			pickUpEastFrames[i] = load(robertPath, i*71, PICKUP_Y_OFF + FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
			pickUpSouthFrames[i] = load(robertPath, i*71, PICKUP_Y_OFF + 2*FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
			pickUpWestFrames[i] = load(robertPath, i*71, PICKUP_Y_OFF + 3*FRAME_HEIGHT, 71, FRAME_HEIGHT, false);
		}
		robertCurrentFrameNum = 0;
		robertCurrentFrame = robertWalkNorthFrames[robertCurrentFrameNum];
		
		// CAT
		catTeleportFrames = new Sprite[numTeleportingFrames];
		catTeleportFrames[0] = load(catPath, 0, 0, 114, 138, false);
		for (int i=1; i<numTeleportingFrames; i++) {
			catTeleportFrames[i] = load(catPath, i*130, 0, 130, 138, false);
		}
	}
	
	public static Sprite getFrame(int state, int frame) {
		switch (state) {
			case Globals.NORTH: 		frame %= numWalkingFrames;	
										return robertWalkNorthFrames[frame];
			case Globals.EAST:			frame %= numWalkingFrames;
										return robertWalkEastFrames[frame];
			case Globals.SOUTH:			frame %= numWalkingFrames;
										return robertWalkSouthFrames[frame];
			case Globals.WEST:			frame %= numWalkingFrames;
										return robertWalkWestFrames[frame];
		}
		return robertCurrentFrame;
	}
	
	public static Sprite getFrame(int state, int facing, int frame) {
		if (state == PlayerController.WALKING) {
			switch (facing) {
				case Globals.NORTH: 		frame %= numWalkingFrames;	
											return robertWalkNorthFrames[frame];
				case Globals.EAST:			frame %= numWalkingFrames;
											return robertWalkEastFrames[frame];
				case Globals.SOUTH:			frame %= numWalkingFrames;
											return robertWalkSouthFrames[frame];
				case Globals.WEST:			frame %= numWalkingFrames;
											return robertWalkWestFrames[frame];
			}
		} else if (state == PlayerController.INIT_CARRYING) {
			switch (facing) {
				case Globals.NORTH: 		frame %= numPickUpFrames;	
											return pickUpNorthFrames[frame];
				case Globals.EAST:			frame %= numPickUpFrames;
											return pickUpEastFrames[frame];
				case Globals.SOUTH:			frame %= numPickUpFrames;
											return pickUpSouthFrames[frame];
				case Globals.WEST:			frame %= numPickUpFrames;
											return pickUpWestFrames[frame];
			}
		} else if (state == PlayerController.CARRYING) {
			switch (facing) {
				case Globals.NORTH: 		frame %= numWalkingFrames;	
											return carryNorthFrames[frame];
				case Globals.EAST:			frame %= numWalkingFrames;
											return carryEastFrames[frame];
				case Globals.SOUTH:			frame %= numWalkingFrames;
											return carrySouthFrames[frame];
				case Globals.WEST:			frame %= numWalkingFrames;
											return carryWestFrames[frame];
			}
		}
		return robertCurrentFrame;
	}
	
	public static Sprite getCatFrame(int state, int facing, int frame) {
		if (state == CatController.TELEPORT_IN) {
			frame %= numTeleportingFrames;
			return catTeleportFrames[frame];
		} else if (state == CatController.TELEPORT_OUT) {
			frame %= numTeleportingFrames;
			return catTeleportFrames[frame];
		}
		return catCurrentFrame;
	}
	
	public static Sprite load(String name, int x, int y, int width, int height, boolean flipY) {
		Texture texture = new Texture(Gdx.files.internal(name));
		Sprite region = new Sprite(texture, x, y, width, height);
		if (flipY) region.flip(false, true);
		return region;
	}
}
