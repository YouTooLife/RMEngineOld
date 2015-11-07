package net.youtoolife.tools.models;

import com.badlogic.gdx.math.Rectangle;

import net.youtoolife.tools.screens.Surface;

public class AngryStar extends OpponentCore {
	
	boolean load = false;
	float animSpeed = 0.05f;
	int way = 0;
	
	public AngryStar(Opponent opponent) {
		super(opponent);
	}

	
	@Override
	public int core(float delta) {
		super.core(delta);
		
		Opponent opp = getOpp();
		if (!load) {
			opp.setRegion(0, 0, 128, 128);
			opp.setSize(Math.abs(128), Math.abs(128));
			opp.setOrigin(128 / 2, 128/ 2);
			opp.setAnimation(getOpp().getTexture(), 3, 1, 0, 0, 0.3f, false);
			//opp.setAnimation(getOpp().getTexture(), 3, 1, 0, 2, animSpeed, true);
			load = true;
		}
		if (way != getWay()) {
			way = getWay();
			if (getWay() == 0)
					opp.setAnimation(getOpp().getTexture(), 3, 1, 0, 0, animSpeed, false);
			else
			opp.setAnimation(getOpp().getTexture(), 3, 1, 1, 2, animSpeed, true);
			switch (getWay()) {
			case 1:
				opp.setRotation(0);
			break;
			case 2:
				opp.setRotation(90);
			break;
			case 3:
				opp.setRotation(90*2);
			break;
			case 4:
				//System.out.println(opp.getX()+":"+opp.getY());
				opp.setRotation(90*3);
				//System.out.println(opp.getX()+":"+opp.getY());
			break;
			default:
				break;
			}
			
		}
		//switch (getWay()) {
		/*case 0:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 0, 0, animSpeed, false);
			switch (getOldWay()) {
			case 1:
				//opp.setRotation(0);
				break;
			case 2:
				//opp.setRotation(90);
				break;
			case 3:
				opp.setRotation(90*2);
				break;	
			case 4:
				opp.setRotation(90*3);
				break;	
			default:
				opp.setRotation(0);
				break;
			}
			break;*/
		/*case 1:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 1, 2, animSpeed, true);
			//opp.setRotation(0);
		break;
		case 2:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 1, 2, animSpeed, true);
			//opp.setRotation(90);
		break;
		case 3:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 1, 2, animSpeed, true);
			//opp.setRotation(90*2);
		break;
		case 4:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 1, 2, animSpeed, true);
			//opp.setRotation(90*3);
		break;
		default:
			opp.setAnimation(getOpp().getTexture(), 3, 1, 0, 0, animSpeed, false);
			break;
		}*/
		return 0;
	}

}


/*public int find(float x, float y, Array<Vector2> way1) {

if (curStep > 25)
	return 0;

Vector2 vec = new Vector2(x, y);
//System.out.println(x+" "+y);
oldWay.add(vec);

curStep++;

if (curStep > minStep && minStep > 0) {
	oldWay.removeIndex(oldWay.size-1);
	return 0;
}

Array<Vector2> way = new Array<Vector2>();
way.addAll(way1);
way.add(vec);

Player player = Surface.pack.getPlayer();
bounds.setPosition(x, y);
if (player.getBoundingRectangle().overlaps(bounds)) {
//if (player.getY() <= bounds.getY()) {
	System.out.println("Find");
	if (minStep < 0 || curStep < minStep) {
		System.out.println("Find-"+curStep);
		minStep = curStep;
		minWay.clear();
		minWay.addAll(way);
		//System.out.println("F "+minWay.size+" "+minStep);
	}
	
} else {
	float maxX = (player.getX()>getOpp().getX()?player.getX()+128*3:getOpp().getX()+128*3);
	float minX = (player.getX()>getOpp().getX()?getOpp().getX()-128*3:player.getX()-128*3);
	float maxY = (player.getY()>getOpp().getY()?player.getY()+128*3:getOpp().getY()+128*3);
	float minY = (player.getY()>getOpp().getY()?getOpp().getY()-128*3:player.getY()-128*3);
if (x+128<maxX&&!isOldWay(new Vector2(vec.x+128, vec.y))&&!isWall(x+128, y))
	find(x+128, y, way);
if (x-128>minX&&!isOldWay(new Vector2(vec.x-128, vec.y))&&!isWall(x-128, y))
	find(x-128, y, way);
if (y+128<maxY&&!isOldWay(new Vector2(vec.x, vec.y+128))&&!isWall(x, y+128))
	find(x, y+128, way);
if (y-128>minY&&!isOldWay(new Vector2(vec.x, vec.y-128))&&!isWall(x, y-128))
	find(x, y-128, way);
}
curStep--;
oldWay.removeIndex(oldWay.size-1);
return 0;
}
*/
