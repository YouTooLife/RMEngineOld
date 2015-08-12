package net.youtoolife.tools.models;

import net.youtoolife.tools.handlers.RMESprite;
import net.youtoolife.tools.screens.Surface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends RMESprite {
	
	boolean run = true;
	int speed = 450;
	float s, y = 0;
	//Circle bounds;
	 public Rectangle bounds;
	 public float rot = 0;
	 public Color color = new Color(1.f, 1.f, 1.f, 0.f);
	

	public Bullet(Texture ws, float x, float y, float rot) {
		super(ws, x, y);
		//bounds = new Circle(x, y, ws.getWidth());
		//bounds = new Rectangle(x, y, ws.getWidth()/9f*7, ws.getHeight()/9f*7);
		bounds = new Rectangle(x, y, 32/9f*7, 32/9f*7);
		if (rot > 360)
		this.rot = rot % 360;
		else
			this.rot = rot;	
		this.y = y;
		System.out.println("R- "+this.rot+"  x - "+getX()+" Y - "+getY()+" nYx50t45 = "+((getX())*Math.tan((this.rot/180*Math.PI))));
	}
	
	public Bullet(Texture ws, int frame_cols, int frame_rows, int animStart, int animStop, boolean animActive, float animSpeed) {
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
		//int max = 20;
		
		if (run) {
			s+=delta*speed;
		if (rot < 90 && rot > 0)
		this.setPosition(getX()+delta*speed, y+(float)( (s)*Math.tan(rot/180*Math.PI)) );
		if (rot > 90 && rot < 180)
			this.setPosition(getX()-delta*speed, y-(float)( (s)*Math.tan(rot/180*Math.PI)) );
		if (rot > 180 && rot < 270)
			this.setPosition(getX()-delta*speed, y-(float)( (s)*Math.tan(rot/180*Math.PI)) );
		if (rot > 270 && rot < 360)
			this.setPosition(getX()+delta*speed, y+(float)( (s)*Math.tan(rot/180*Math.PI)) );
		
		/*for (int i = 0; i < Surface.walls.size; i++)
		if (Surface.walls.get(i).getBoundingRectangle().overlaps(bounds)) {
			 run = false;
			}*/
		
		//this.rotate(delta*500);
		}
	}

	private void inputHandler(float delta) {
		
	}

}