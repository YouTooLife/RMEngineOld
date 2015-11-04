package net.youtoolife.tools.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

import net.youtoolife.tools.screens.Surface;

public class AngryStar extends OpponentCore {
	
	//Player player;
	Array<Wall> walls = new Array<Wall>();
	float speed = 70;
	
	public AngryStar(Opponent opponent) {
		super(opponent);
	}
	
	public void createWave() {
		
	}
	
	
	
	@Override
	public int core(float delta) {
		//float speedX = getOpp().speedX;
		//float speedY = getOpp().speedY;
		/*Player player = Surface.pack.getPlayer();
		if (player == null)
				return 1;
		Array<Wall> walls = Surface.pack.getWalls();
		if (Math.abs(player.getX()-getOpp().getX()) >
				Math.abs(player.getY()-getOpp().getY()))
		{
			if (player.getX() > getOpp().getX()) {
			if (walls != null)
				for (Wall wall: walls)
					if (wall.getX() > getOpp().getX())
						this.walls.add(wall);
				int maxX = (int)((player.getX()+player.getWidth()+128*2 - (getOpp().getX()+getOpp().getWidth()))/128);
				int m[][] = new int[7][maxX];
				for (int y = 0; y < 7; y++)
					for (int x = 0; x < maxX; x++)
						for (Wall wall:this.walls)
							if (wall.getBoundingRectangle().contains(getOpp().getX()+x*128, 
									(getOpp().getY()-3*128)+y*128))
						m[6-y][x] = 1;
							else
						m[6-y][x] = 0;
				for (int y = 0; y < 7; y++) {
					for (int x = 0; x < maxX; x++)
						System.out.print(m[y][x]+" ");
					System.out.print("\n");
				}
			}
		}*/
		/*if (player.getX() > getOpp().getX() && speedX < 6) speedX += delta*100;
		if (player.getX() < getOpp().getX() && speedX > -6) speedX -= delta*100;
		if (player.getY() > getOpp().getY() && speedY < 6) speedY += delta*100;
		if (player.getY() < getOpp().getY() && speedY > -6) speedY -= delta*100;
		if (player.getX() > getOpp().getX()) {
			getOpp().setPosition(getOpp().getX()+speed*delta, getOpp().getY());
		}
		if (player.getX() < getOpp().getX()) {
			getOpp().setPosition(getOpp().getX()-speed*delta, getOpp().getY());
		}
		if (player.getY() < getOpp().getY()) {
			getOpp().setPosition(getOpp().getX(), getOpp().getY()-speed*delta);
		}
		if (player.getY() > getOpp().getY()) {
			getOpp().setPosition(getOpp().getX(), getOpp().getY()+speed*delta);
		}*/
		//getOpp().setPosition(getOpp().getX()+speedX, getOpp().getY()+speedY);
		//super.core(delta);
		return 0;
	}

}
