package de.jcup.ditaa.model;

import java.util.ArrayList;
import java.util.List;

public class Box implements Shape{
	
	private Point edgeLeftTop;
	private Point edgeLeftBottom;
	private Point edgeRightTop;
	private Point edgeRightBottom;

	private List<Point> points = new ArrayList<>();
	private List<Line> lines= new ArrayList<>();
	
	public Box(Point edgeLeftTop, int width, int height) {
		this.edgeLeftTop=edgeLeftTop;
		
		this.edgeLeftBottom=new Point(edgeLeftTop.x,edgeLeftTop.y+height,'+');
		this.edgeRightTop=new Point(edgeLeftTop.x+width,edgeLeftTop.y,'+');
		this.edgeRightBottom=new Point(edgeLeftTop.x+width,edgeLeftTop.y+height,'+');
	
		this.points.add(edgeLeftTop);
		this.points.add(edgeLeftBottom);
		this.points.add(edgeRightTop);
		this.points.add(edgeRightBottom);
		
		lines.add(new Line(edgeLeftTop,edgeRightTop));
		lines.add(new Line(edgeRightTop,edgeRightBottom));
		lines.add(new Line(edgeRightBottom,edgeLeftBottom));
		lines.add(new Line(edgeLeftBottom,edgeLeftTop));
	}
	
	public Point getEdgeLeftBottom() {
		return edgeLeftBottom;
	}
	public Point getEdgeLeftTop() {
		return edgeLeftTop;
	}
	public Point getEdgeRightTop() {
		return edgeRightTop;
	}
	
	public Point getEdgeRightBottom() {
		return edgeRightBottom;
	}
	

	@Override
	public void draw(Scene targetScene) {
		for (Line line: lines){
			line.draw(targetScene);
		}
	}
}
