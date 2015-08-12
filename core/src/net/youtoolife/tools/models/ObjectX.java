package net.youtoolife.tools.models;

import net.youtoolife.tools.handlers.RMESprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ObjectX extends RMESprite implements Json.Serializable {
	
	public Rectangle bounds;
	
	public ObjectX() {
		 super();
		 bounds = new Rectangle(0,0, 128, 128);
	}
	
	public ObjectX(Texture ws, float x, float y) {
		super(ws, x, y);
		bounds = new Rectangle(x, y, 128, 128);
	}
	
	public ObjectX(Texture ws, int frame_cols, int frame_rows, int animStart, int animStop, boolean animActive, float animSpeed) {
		super(ws, frame_cols, frame_rows, animStart, animStop, animActive, animSpeed);
	}
	
	public void update(float delta) {
		draw(delta);
		bounds.setPosition(getX()+bounds.width, getY()+bounds.height);
	}
	
	@Override
	public void write(Json json) {
		//json.writeValue("hp", hp);
		super.write(json);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		//hp = jsonData.getInt("hp");
		super.read(json, jsonData);
	}
}
