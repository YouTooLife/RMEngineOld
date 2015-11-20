package net.youtoolife.tools.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import net.youtoolife.tools.handlers.RMESprite;
import net.youtoolife.tools.screens.Surface;

public class OpponentCore {
	
	private Opponent opp;
	
	float speedX = 70, speedY = 70;
	
	boolean goCenter = false;
	boolean goTolim = false, center = false;
	
	int limX;
	int limY;
	boolean limXX, limYY;
	
	public Rectangle bounds;
	
	public int way = 0, oldWay = 1;
	
	public OpponentCore(Opponent opponent) {
		this.opp = opponent;
	}
	
	public boolean isWall(float x, float y) {
		if (Surface.pack.getWalls() != null)
		for (Wall wall: Surface.pack.getWalls())
			if (wall.getX() == x && wall.getY() == y)
				return true;
		return false;
	}
	
	public boolean isOpponent(float x, float y) {
		if (Surface.pack.getOpps() != null)
			for (int i = 0; i < Surface.pack.getOpps().size; i++)
			{
				Opponent opp = Surface.pack.getOpps().get(i);
				Rectangle bounds = new Rectangle(x, y, 128, 128);
				if (opp != getOpp() && opp.getBoundingRectangle().overlaps(bounds))
						return true;
			}
		/*for (Opponent opp: Surface.pack.getOpps())
			if (/*opp != getOpp() && opp.getX() == x && opp.getY() == y)
				return true;*/
		return false;
	}
	
	public boolean isPlayer(float x, float y) {
		Player player = Surface.pack.getPlayer();
		if (player != null) {
			
				Rectangle bounds = new Rectangle(x, y, 128, 128);
				if (player.getBoundingRectangle().overlaps(bounds))
						return true;
			}
		return false;
	}
	
	public boolean isSurface(float x, float y) {
		if (Surface.pack.getSurface() != null)
		for (SurfaceX opp: Surface.pack.getSurface())
			if (opp.getX() == x && opp.getY() == y)
				return true;
		return false;
	}
	
	public boolean isDoor(float x, float y) {
		if (Surface.pack.getDoors() != null)
		for (Door door: Surface.pack.getDoors())
			if (door.getX() == x && door.getY() == y)
				if (((getOpp().getColor().r*1000000
						+getOpp().getColor().g*1000
						+getOpp().getColor().b) !=
				(door.getColor().r*1000000+door.getColor().g*1000+door.getColor().b)))
				return true;
		return false;
	}
	
	public boolean canGo(float x, float y) {
		if (!isWall(x, y)&&!isOpponent(x, y)&&isSurface(x, y)&&!isPlayer(x, y)&&!isDoor(x, y))
			return true;
		else
			return false;
	}
	
	public int go(float delta) {
		if (!limXX) {
			if (limX == getOpp().getX()) {
				limXX = true;
				return 1;
			}
		if (limX > getOpp().getX()) {
			//System.out.println("~x"+getOpp().getX()+"/"+limX);
			way = 4;
			getOpp().setPosition(getOpp().getX()+speedX*delta, getOpp().getY());
			if (limX <= getOpp().getX()){
				//System.out.println("x"+getOpp().getX()+"/"+limX);
				limXX = true;
				return 1;
			}
		}
		
		if (limX < getOpp().getX()) {
			//System.out.println("~-x"+getOpp().getX()+"/"+limX);
			way = 2;
			getOpp().setPosition(getOpp().getX()-speedX*delta, getOpp().getY());
			if (limX >= getOpp().getX()){
				//System.out.println("x"+getOpp().getX()+"/"+limX);
				limXX = true;
				return 1;
			}
		}
		}
		if (!limYY && limXX) {
			
			if (limY == getOpp().getY()) {
				limYY = true;
				return 2;
			}
		if (limY > getOpp().getY()) {
			//System.out.println("y");
			way = 1;
			getOpp().setPosition(getOpp().getX(), getOpp().getY()+speedY*delta);
			//return 1;
			if (limY <= getOpp().getY()){
				//System.out.println("+y"+getOpp().getY()+"/"+limY);
				limYY = true;
				return 2;
			}
		}
		
		if (limY < getOpp().getY()) {
			//System.out.println("y-");
			way = 3;
			getOpp().setPosition(getOpp().getX(), getOpp().getY()-speedY*delta);
			//return 1;
			if (limY >= getOpp().getY()){
				//System.out.println("-y"+getOpp().getY()+"/"+limY);
				limYY = true;
				return 2;
			}
		}
		}
		return 0;
	}
	
	public int getLim() {
		Player player = Surface.pack.getPlayer();
		long px = (int)(player.getX()/128)*128;
		long py = (int)(player.getY()/128)*128;
		if (getOpp().getX() < px) {
			if (canGo(getOpp().getX()+128, getOpp().getY())) {
				limX = (int) (getOpp().getX()+128);
				limY = (int) getOpp().getY();
				//System.out.println(limX+":"+limY);
				return 1;
			}
		}
		if (getOpp().getX() > px) {
			if (canGo(getOpp().getX()-128, getOpp().getY())) {
				limX = (int) (getOpp().getX()-128);
				limY = (int) getOpp().getY();
				//System.out.println(limX+":"+limY);
				return 1;
			}
		}
		if (getOpp().getY() < py) {
			if (canGo(getOpp().getX(), getOpp().getY()+128)) {
				limX = (int) (getOpp().getX());
				limY = (int) (getOpp().getY()+128);
				//System.out.println(limX+":"+limY);
				return 1;
			}
		}
		if (getOpp().getY() > py) {
			if (canGo(getOpp().getX(), getOpp().getY()-128)) {
				limX = (int) (getOpp().getX());
				limY = (int) (getOpp().getY()-128);
				//System.out.println(limX+":"+limY);
				return 1;
			}
		}
		return 0;
	}
	
	public void collisionDoor() {
		Array<Door> doors = Surface.pack.getDoors();
		if (doors != null)
		for (int i = 0; i < doors.size; i++) {
			Door door = doors.get(i);
		if (door.getBoundingRectangle().overlaps(getOpp().getBoundingRectangle())) {
			if (((getOpp().getColor().r*1000000+
					getOpp().getColor().g*1000+
					getOpp().getColor().b) ==
				(door.getColor().r*1000000+door.getColor().g*1000+door.getColor().b)))
			//flag = true;
				doors.removeValue(door, false);
				//wallForce(door);
			//System.out.println((getColor().r*1000000+getColor().g*1000+getColor().b));
			//System.out.println((door.getColor().r*1000000+door.getColor().g*1000+door.getColor().b));
		} 
		}
	}
	
	public int core(float delta) {
		if (!center )
		if (!goCenter && (getOpp().getX() % 128 != 0 || getOpp().getY() % 128 != 0)) {
			limX = (int)(getOpp().getX()/128)*128;
			limY = (int)(getOpp().getY() / 128)*128;
			goCenter = true;
		}
		else {
			goCenter = false;
			center = true;
		}
		
		if (goCenter && !center)
			goCenter(delta);
		
		if (center) {
			
			if (!goTolim)
			if (getLim() == 1) {
				limXX = false;
				limYY = false;
				goTolim = true;
			}
			if (goTolim) {
				int res = go(delta);
				if (res == 1)
						getOpp().setPosition(limX, getOpp().getY());
				if (res == 2)
					getOpp().setPosition(getOpp().getX(), limY);
				
				if (limXX && limYY) {
					//System.out.println("~lim");
					goTolim = false;
					oldWay = way;
					way = 0;
				}
			}
		}
		collisionDoor();
		return 0;
	}
	
	public int goCenter(float delta) {
		if (goCenter) {
			if (getOpp().getX() % 128 != 0) {
				
				
				//System.out.println(getOpp().getX()+" "+limX);
				if (getOpp().getX() > limX) {
					getOpp().setPosition(getOpp().getX()-speedX*delta, getOpp().getY());
					return 1;
				}
				else {
					if (getOpp().getX() < limX)
						getOpp().setPosition(limX, getOpp().getY());
					return 1;
				}
				
			}
			if (getOpp().getY() % 128 != 0) {
				
				if (getOpp().getY() > limY) {
					getOpp().setPosition(getOpp().getX(), getOpp().getY()-speedY*delta);
					return 1;
				}
				else {
					if (getOpp().getY() < limY)
						getOpp().setPosition(getOpp().getX(), limY);
					goCenter = false;
					center = true;
					return 1;
				}
				
			}
		}
		
		return 0;
	}
	
	public Opponent getOpp() {
		return opp;
	}
	
	public int getWay() {
		return way;
	}
	public int getOldWay() {
		return oldWay;
	}

}