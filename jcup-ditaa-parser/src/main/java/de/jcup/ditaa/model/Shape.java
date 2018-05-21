package de.jcup.ditaa.model;

import java.util.List;

public interface Shape {

	public void draw(Scene targetScene);
	
	public List<Point> getPoints();
}
