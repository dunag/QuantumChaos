package com.theboxbrigade.quantumchaos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.theboxbrigade.quantumchaos.general.Assets;
import com.theboxbrigade.quantumchaos.general.Globals;
import com.theboxbrigade.quantumchaos.general.Input;

public class CreditsScreen extends Screen {
	private static final String text = "The Box Brigade\n\n" +
			"Spencer Brint - Audio Lead\n" +
			"Charles Dungan - Story Lead / Producer\n" +
			"Ashley Lau - Art Lead\n" +
			"Jason Simmons - Technical Lead\n\n" +
			"Special thanks to Dave Small!!!";
	public CreditsScreen() {
		System.out.println("CreditsScreen");
		Assets.menuMusic.setLooping(true);
		Assets.menuMusic.play();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		Assets.font.setColor(Color.WHITE);
		Assets.font.setScale(1.5f, -1.5f);
		Assets.font.draw(spriteBatch, text, Globals.GAME_WIDTH - 200, Globals.GAME_HEIGHT);
		Assets.font.setScale(1.0f, 1.0f);
		Assets.font.setColor(Color.BLACK);
		spriteBatch.end();
	}
	
	@Override
	public void tick(Input input) {
		if (input.buttons[Input.AFFIRM] && !input.oldButtons[Input.AFFIRM])
		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			Assets.menuMusic.stop();
			setScreen(new MainMenuScreen());
			input.releaseAllKeys();
		}
	}
}