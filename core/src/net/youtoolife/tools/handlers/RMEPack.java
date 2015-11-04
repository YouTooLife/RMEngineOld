package net.youtoolife.tools.handlers;

import net.youtoolife.tools.models.CheckPoint;
import net.youtoolife.tools.models.Door;
import net.youtoolife.tools.models.ObjectX;
import net.youtoolife.tools.models.Player;
import net.youtoolife.tools.models.SurfaceX;
import net.youtoolife.tools.models.Wall;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class RMEPack implements Json.Serializable {
	
	private Player player;
	private Array<SurfaceX> surface;
	private Array<Wall> walls;
	private Array<Door> doors;
	private Array<CheckPoint> checkPoints;
	private Array<ObjectX> objects;
	
	private boolean game = false;
	
	public RMEPack() {
		
	}

	public RMEPack(Player player, Array<RMESprite> arr) {
		this.setPlayer(player);
	}

	public Player getPlayer() {
		if (player != null)
			return player;
		else
			return null;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void del(float x, float y) {
		if (surface != null)
		for (SurfaceX sur:surface)
			if (sur.getBoundingRectangle().contains(x+128/2, y+128/2))
				surface.removeValue(sur, false);
		
		if (walls != null)
			for (Wall sur:walls)
				if (sur.getBoundingRectangle().contains(x+128/2, y+128/2))
					walls.removeValue(sur, false);
		if (doors != null)
			for (Door sur:doors)
				if (sur.getBoundingRectangle().contains(x+128/2, y+128/2))
					doors.removeValue(sur, false);
		if (objects != null)
			for (ObjectX sur:objects)
				if (sur.getBoundingRectangle().contains(x+128/2, y+128/2))
					objects.removeValue(sur, false);
		if (checkPoints != null)
			for (CheckPoint sur:checkPoints)
				if (sur.getBoundingRectangle().contains(x+128/2, y+128/2))
					checkPoints.removeValue(sur, false);
		
		
	}
	
	public void addSurface(SurfaceX sur) {
		if (surface == null)
			surface = new Array<SurfaceX>();
		surface.add(sur);
	}
	
	public void addWall(Wall wall) {
		if (getWalls() == null)
			setWalls(new Array<Wall>());
		getWalls().add(wall);
	}
	
	public void addDoor(Door door) {
		if (getDoors() == null)
			setDoors(new Array<Door>());
		getDoors().add(door);
	}
	
	public void addCheckPoint(CheckPoint checkPoint) {
		if (getCheckPoints() == null)
			setCheckPoints(new Array<CheckPoint>());
		getCheckPoints().add(checkPoint);
	}
	
	public void addObject(ObjectX object) {
		if (getObjects() == null)
			setObjects(new Array<ObjectX>());
		getObjects().add(object);
	}


	@Override
	public void write(Json json) {
		json.writeValue("surface", surface);
		json.writeValue("walls", getWalls());
		json.writeValue("doors", doors);
		json.writeValue("checkPoints", checkPoints);
		json.writeValue("objects", objects);
		json.writeValue("player", player);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		surface = json.readValue("surface", Array.class, jsonData);
		setWalls(json.readValue("walls", Array.class, jsonData));
		doors = json.readValue("doors", Array.class, jsonData);
		checkPoints = json.readValue("checkPoints", Array.class, jsonData);
		objects = json.readValue("objects", Array.class, jsonData);
		player = json.readValue("player", Player.class, jsonData);
	}
	
	public void update (float delta) {
		if (surface != null)
		for (SurfaceX sur:surface)
			sur.update(delta);
		if (getWalls() != null)
			for (Wall sur:getWalls())
				sur.update(delta);
		if (getDoors() != null)
			for (Door sur:getDoors())
				sur.update(delta);
		if (getObjects() != null)
			for (ObjectX sur:getObjects())
				sur.update(delta);
		if (getCheckPoints() != null)
			for (CheckPoint sur:getCheckPoints())
				sur.update(delta);
		
		if (player != null)
			if (isGame())
			player.update(delta);
	}
	
	public void draw(SpriteBatch batcher) {
		if (surface != null)
		for (SurfaceX sur:surface)
			if (!sur.isDraw())
			sur.draw(batcher);
		if (walls != null)
			for (Wall sur:walls)
				if (!sur.isDraw())
				sur.draw(batcher);
		if (doors != null)
			for (Door sur:doors)
				if (!sur.isDraw())
				sur.draw(batcher);
		if (objects != null)
			for (ObjectX sur:objects)
				if (!sur.isDraw())
				sur.draw(batcher);
		if (checkPoints != null)
			for (CheckPoint sur:checkPoints)
				if (!sur.isDraw())
				sur.draw(batcher);

		if (player != null)
			player.draw(batcher);
	}
	
	public void drawShape(ShapeRenderer shapeRenderer) {
		if (surface != null)
		for (SurfaceX sur:surface)
			{
				if (sur.isDraw()) {
				shapeRenderer.setColor(sur.getColor());
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
				}
			}
		if (getWalls() != null)
			for (Wall sur:getWalls())
			{
				if (sur.isDraw()) {
					shapeRenderer.setColor(sur.getColor());
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
				}
			}
		if (getDoors() != null)
			for (Door sur:getDoors())
			{
				if (sur.isDraw()) {
					shapeRenderer.setColor(sur.getColor());
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
				}
			}
		if (getObjects() != null)
			for (ObjectX sur:getObjects())
			{
				if (sur.isDraw()) {
					shapeRenderer.setColor(sur.getColor());
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
				}
			}
		if (getCheckPoints() != null)
			for (CheckPoint sur:getCheckPoints())
			{
				if (sur.isDraw()) {
					shapeRenderer.setColor(sur.getColor());
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
				}
			}
	}
	
	public void drawShapeLine(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(new com.badlogic.gdx.graphics.Color(0.f, 0.f, 0.f, 1.f));
		if (surface != null)
		for (SurfaceX sur:surface)
			{
				//shapeRenderer.setColor(sur.getColor());
			if (sur.isRect())
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
			}
		if (getWalls() != null)
			for (Wall sur:getWalls())
			{
				//shapeRenderer.setColor(sur.getColor());
				if (sur.isRect())
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
			}
		if (getDoors() != null)
			for (Door sur:getDoors())
			{
				//shapeRenderer.setColor(sur.getColor());
				if (sur.isRect())
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
			}
		if (getObjects() != null)
			for (ObjectX sur:getObjects())
			{
				//shapeRenderer.setColor(sur.getColor());
				if (sur.isRect())
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
			}
		if (getCheckPoints() != null)
			for (CheckPoint sur:getCheckPoints())
			{
				//shapeRenderer.setColor(sur.getColor());
				if (sur.isRect())
				shapeRenderer.rect(sur.getX(), sur.getY(), sur.getWidth(), sur.getHeight());
			}
	}

	public Array<Wall> getWalls() {
		return walls;
	}
	
	public Array<Door> getDoors() {
		return doors;
	}
	
	public Array<ObjectX> getObjects() {
		return objects;
	}
	public Array<CheckPoint> getCheckPoints() {
		return checkPoints;
	}
	
	public Array<SurfaceX> getSurface() {
		return surface;
	}

	public void setWalls(Array<Wall> walls) {
		this.walls = walls;
	}
	
	public void setDoors(Array<Door> doors) {
		this.doors = doors;
	}
	
	public void setObjects(Array<ObjectX> objects) {
		this.objects = objects;
	}
	public void setCheckPoints(Array<CheckPoint> checkPoins) {
		this.checkPoints = checkPoins;
	}

	public boolean isGame() {
		return game;
	}

	public void setGame(boolean game) {
		this.game = game;
	}
}
