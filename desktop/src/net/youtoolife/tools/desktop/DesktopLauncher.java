package net.youtoolife.tools.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.youtoolife.tools.RMEBuilder;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1330;
		config.height = 764;
		new LwjglApplication(new RMEBuilder(), config);
	}
}