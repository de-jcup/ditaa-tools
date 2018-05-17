package de.jcup.ditaa.model;

import java.util.Map;
import java.util.TreeMap;

import de.jcup.ditaa.model.Matrix.Dimension;

public class Scene {

	private Matrix matrix = new Matrix();

	public void add(Point point) {
		matrix.add(point.x, point.y, point.character);
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		Dimension dimension = matrix.getDimension();
		for (int y = 0; y < dimension.getHeight(); y++) {
			for (int x = 0; x < dimension.getWidth(); x++) {
				int v = matrix.getValueAt(x, y);
				if (v == -1) {
					sb.append(" ");
				} else {
					sb.append((char) v);
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
