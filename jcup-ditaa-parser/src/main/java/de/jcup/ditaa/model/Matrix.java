package de.jcup.ditaa.model;

import java.util.Map;
import java.util.TreeMap;

public class Matrix {
	
	private Map<Integer, Map<Integer,Character>> matrix = new TreeMap<>();
	private Dimension dimension; 
	
	public void add(int x, int y, char c) {
		dimension=null;
		
		Map<Integer, Character> xParts = matrix.get(x);
		if (xParts==null){
			xParts=new TreeMap<>();
			matrix.put(x, xParts);
		}
		
		xParts.put(y, c);
	}
	
	public class Dimension{
		private int width;
		private int height;
		
		private Dimension(int width, int height){
			this.width=width;
			this.height=height;
		}
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	public int getValueAt(int x, int y){
		Map<Integer, Character> data = matrix.get(x);
		if (data==null){
			return -1;
		}
		Character value = data.get(y);
		if (value==null){
			return -1;
		}
		return value.charValue();
	}
	public Dimension getDimension(){
		if (dimension==null){
			dimension = calcDimension();
		}
		return dimension;
	}

	private Dimension calcDimension(){
		int maxX = 0;
		int maxY = 0;
		for (Integer xValue: matrix.keySet()){
			int x = xValue.intValue();
			if (x>maxX){
				maxX=x;
			}
			Map<Integer, Character> xParts = matrix.get(xValue);
			for (Integer yValue: xParts.keySet()){
				int y = yValue.intValue();
				if (y>maxY){
					maxY=y;
				}
			}
		}
		return new Dimension(maxX+1, maxY+1);
	}
	
}
