package net.youtoolife.tools.models;


import net.youtoolife.tools.handlers.RMESprite;
import net.youtoolife.tools.screens.Surface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Player extends RMESprite implements Json.Serializable {
	
	 int speed = 250;
	 float speedX = 0.f, speedY = 0.f;
	//Circle bounds;
	 public Rectangle bounds;
	 public float rot = 0;
	 
	 public Color color = new Color(1.f, 1.f, 1.f, 0.f);
	 
	 private int hp = 100;
	 private float sX, sY;
	 
	 public Player() {
		 super();
		 bounds = new Rectangle(0,0, 128/9f*7, 128/9f*7);
	 }
	

	public Player(Texture ws, float x, float y) {
		super(ws, x, y);
		//bounds = new Circle(x, y, ws.getWidth());
		//bounds = new Rectangle(x, y, ws.getWidth()/9f*7, ws.getHeight()/9f*7);
		bounds = new Rectangle(x, y, 128/9f*7, 128/9f*7);
	}
	
	public Player(Texture ws, int frame_cols, int frame_rows, int animStart, int animStop, boolean animActive, float animSpeed) {
		super(ws, frame_cols, frame_rows, animStart, animStop, animActive, animSpeed);
		//bounds = new Circle(0, 0, ws.getWidth());
		//bounds = new Rectangle(0, 0, ws.getWidth()/9f*7, ws.getHeight()/9f*7);
	}
	
	public void update(float delta) {
		draw(delta);
		//bounds.setPosition(getX()+getWidth()/2, getY()+getHeight()/2);
		//bounds.setRadius(getRegionWidth()/2-getHeight()/2/8);
		bounds.setPosition(getX()+bounds.width/9f*1.3f, getY()+bounds.height/9f*1.3f);
		inputHandler(delta);
		int max = 20;
		Array<Wall> walls = Surface.pack.getWalls();
		if (walls != null)
		for (int i = 0; i < Surface.pack.getWalls().size; i++)
		if (walls.get(i).getBoundingRectangle().overlaps(bounds)) {
			color.set(1.f, 0.f, 0.f, 0.f);
			speedX = 0;
			speedY = 0;
			if (bounds.y+bounds.height <= (walls.get(i).getBoundingRectangle().y+max)) {
				System.out.println("DOWN");
				setPosition(getX(), getY()-(bounds.y+bounds.height-walls.get(i).getBoundingRectangle().y));
			}
			if (bounds.y >= (walls.get(i).getBoundingRectangle().y+walls.get(i).getBoundingRectangle().height-max)) {
				System.out.println("UP");
				setPosition(getX(), getY()+(walls.get(i).getBoundingRectangle().y+walls.get(i).getBoundingRectangle().height-bounds.y));
			}
			if (bounds.x+bounds.width <= (walls.get(i).getBoundingRectangle().x+max)) {
				System.out.println("LEFT");
				setPosition(getX()-(bounds.x+bounds.width-walls.get(i).getBoundingRectangle().x), getY());
			}
			if (bounds.x >= (walls.get(i).getBoundingRectangle().x+walls.get(i).getBoundingRectangle().width-max)) {
				System.out.println("RIGHT");
				setPosition(getX()+(walls.get(i).getBoundingRectangle().x+walls.get(i).getBoundingRectangle().width-bounds.x), getY());
			}
		} else
			color.set(1.f, 1.f, 1.f, 0.f);
		if (rot < 360)
		rot+=delta*50;
		else rot = 0;
		this.rotate(delta*50);
	}

	private void inputHandler(float delta) {
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && speedX < 7) speedX += delta*10;
		if (Gdx.input.isKeyPressed(Keys.LEFT) && speedX > -7) speedX -= delta*10;
		if (Gdx.input.isKeyPressed(Keys.UP) && speedY < 7) speedY += delta*10;
		if (Gdx.input.isKeyPressed(Keys.DOWN) && speedY > -7) speedY -= delta*10;
		setPosition(getX()+speedX, getY()+speedY);
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("hp", hp);
		super.write(json);

	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		hp = jsonData.getInt("hp");
		sX = jsonData.getFloat("x");
		sY = jsonData.getFloat("y");
		super.read(json, jsonData);

	}

}
