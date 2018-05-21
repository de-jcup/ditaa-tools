package de.jcup.ditaa.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Scene {

	private Matrix matrix = new Matrix();

	public void add(Point point) {
		matrix.add(point.x, point.y, point.character);
	}
	
	public Dimension getDimension(){
		return matrix.getDimension();
	}

	public static Scene fromString(String string){
		Scene scene = new Scene();
		if (string==null || string.trim().isEmpty()) {
			return scene;
		}
		int y=0;
		String[] lines = string.split("\n");
		for (String line: lines){
			char[] chars = line.toCharArray();
			for (int x=0;x<chars.length;x++){
				char c = chars[x];
				scene.add(new Point(x,y,c));
			}
			y++;
		}
		return scene;
	}
	public String print() {
		return print(false);
	}
	public String print(boolean showGrid) {
		StringBuilder sb = new StringBuilder();
		Dimension dimension = matrix.getDimension();
		if (showGrid){
			sb.append("....");
			int p=0;
			for (int x = 0; x < dimension.getWidth(); x++) {
				sb.append(p);
				p++;
				if (p>9){
					p=0;
				}
			}
			sb.append("\n");
		}
		for (int y = 0; y < dimension.getHeight(); y++) {
			if (showGrid){
				String prefix = ""+y;
				while (prefix.length()<4){
					prefix="."+prefix;
				}
				sb.append(prefix);
			}
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
	
	public Point getPoint(Location location) {
		return getPoint(location.x,location.y);
	}
	/**
	 * Returns point or <code>null</code>
	 * @param x
	 * @param y
	 * @return point or <code>null</code>
	 */
	public Point getPoint(int x, int y) {
		int value = matrix.getValueAt(x, y);
		if (value==-1){
			return null;
		}
		return new Point(x,y,(char)value);
	}

	public void remove(Collection<Point> points) {
		for (Point point: points){
			matrix.remove(point);
		}
	}

}
