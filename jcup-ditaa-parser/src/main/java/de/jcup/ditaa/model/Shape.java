package de.jcup.ditaa.model;

import java.util.Set;

public interface Shape {

	public Location getLeftTop();
	
	public void draw(Scene targetScene);
	
	public Set<Point> getPoints();

	public void move(Direction direction);
	
}
