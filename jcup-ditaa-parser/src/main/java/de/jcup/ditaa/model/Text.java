package de.jcup.ditaa.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Text extends AbstractShape {

	private Location edgeLeftTop;

	private List<Point> points = new ArrayList<>();

	public Text(Location edgeLeftTop, String text) {
		this.edgeLeftTop = edgeLeftTop;
		int x = edgeLeftTop.x;
		int y = edgeLeftTop.y;

		for (char c : text.toCharArray()) {
			points.add(new Point(x++, y, c));
		}
	}

	public Location getEdgeLeftTop() {
		return edgeLeftTop;
	}

	@Override
	public void draw(Scene targetScene) {
		for (Point point : points) {
			point.draw(targetScene);
		}
	}

	@Override
	public Set<Point> getPoints() {
		return new LinkedHashSet<>(points);
	}

	@Override
	public Location getLeftTop() {
		return edgeLeftTop;
	}

}
