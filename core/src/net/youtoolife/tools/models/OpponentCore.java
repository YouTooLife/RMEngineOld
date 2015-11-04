package net.youtoolife.tools.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import net.youtoolife.tools.handlers.RMESprite;
import net.youtoolife.tools.screens.Surface;

public class OpponentCore {
	
	private Opponent opp;
	int speed = 250;
	// float speedX = 0.f, speedY = 0.f;
	//Circle bounds;
	 public Rectangle bounds;
	
	public OpponentCore(Opponent opponent) {
		this.opp = opponent;
	}
	
	public void collisionWall() {
		Array<Wall> walls = Surface.pack.getWalls();
		if (walls != null)
		for (int i = 0; i < Surface.pack.getWalls().size; i++)
		if (walls.get(i).getBoundingRectangle().overlaps(bounds)) {
			//color.set(1.f, 0.f, 0.f, 0.f);
			wallForce(walls.get(i));
		}
	}
	
	public void wallForce(RMESprite wall) {
		getOpp().speedX = 0;
		getOpp().speedY = 0;
		int max = 20;
		if (bounds.y+bounds.height <= (wall.getBoundingRectangle().y+max)) {
			System.out.println("DOWN");
			getOpp().setPosition(getOpp().getX(), getOpp().getY()-(bounds.y+bounds.height-wall.getBoundingRectangle().y));
		}
		if (bounds.y >= (wall.getBoundingRectangle().y+wall.getBoundingRectangle().height-max)) {
			System.out.println("UP");
			getOpp().setPosition(getOpp().getX(), getOpp().getY()+(wall.getBoundingRectangle().y+wall.getBoundingRectangle().height-bounds.y));
		}
		if (bounds.x+bounds.width <= (wall.getBoundingRectangle().x+max)) {
			System.out.println("LEFT");
			getOpp().setPosition(getOpp().getX()-(bounds.x+bounds.width-wall.getBoundingRectangle().x), getOpp().getY());
		}
		if (bounds.x >= (wall.getBoundingRectangle().x+wall.getBoundingRectangle().width-max)) {
			System.out.println("RIGHT");
			getOpp().setPosition(getOpp().getX()+(wall.getBoundingRectangle().x+wall.getBoundingRectangle().width-bounds.x), getOpp().getY());
		}
	}
	
	public void collision() {
		bounds = getOpp().bounds;
		collisionWall();
	}
	
	
	public int core(float delta) {
		collision();
		return 0;
	}
	
	public Opponent getOpp() {
		return opp;
	}

}