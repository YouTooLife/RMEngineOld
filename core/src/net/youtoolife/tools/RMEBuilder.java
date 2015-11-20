package net.youtoolife.tools;

import net.youtoolife.tools.handlers.RMESound;
import net.youtoolife.tools.screens.Surface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RMEBuilder extends Game {
	
	public SpriteBatch batcher;
	public static boolean bassMode = false;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		
		if (bassMode)
		RMESound.initNativeBass();
		Assets.load();
		setScreen(new Surface(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
