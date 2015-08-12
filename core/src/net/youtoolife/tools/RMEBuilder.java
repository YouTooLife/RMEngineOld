package net.youtoolife.tools;

import net.youtoolife.tools.handlers.RMESound;
import net.youtoolife.tools.screens.Surface;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RMEBuilder extends Game {
	
	public SpriteBatch batcher;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		
		RMESound.initNativeBass();
		Assets.load();
		setScreen(new Surface(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
