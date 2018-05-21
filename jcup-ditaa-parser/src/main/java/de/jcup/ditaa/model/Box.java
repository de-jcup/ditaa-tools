package de.jcup.ditaa.model;

import java.util.ArrayList;
import java.util.List;

public class Box implements Shape{
	
	private Location edgeLeftTop;
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
	
	public Location getEdgeLeftBottom() {
		return edgeLeftBottom;
	}
	public Location getEdgeLeftTop() {
		return edgeLeftTop;
	}
	public Location getEdgeRightTop() {
		return edgeRightTop;
	}
	
	public Location getEdgeRightBottom() {
		return edgeRightBottom;
	}
	

	@Override
	public void draw(Scene targetScene) {
		for (Line line: lines){
			line.draw(targetScene);
		}
	}

	public static Box from(Scene scene, Location location) {
		if (location==null){
			return null;
		}
		if (scene==null){
			return null;
		}
		Point point = scene.getPoint(location);
		if (point==null){
			return null;
		}
		Box box = new Box(point,1,1);
		if (! box.wouldBePossibleEdge(point)){
			return null;
		}
		box = fromLeftTopToRightTop(scene, box);
		if (box==null){
			return null;
		}
		box = fromRightTopToRightBottom(scene, box);
		if (box==null){
			return null;
		}
		box = fromRightBottomToLeftBottom(scene, box);
		if (box==null){
			return null;
		}
		box = fromLeftBottomToLeftTop(scene, box);
		if (box==null){
			return null;
		}
		return box;
	}

	private static Box fromLeftTopToRightTop(Scene scene, Box box) {
		int y= box.edgeLeftTop.y;
		List<Point> points = new ArrayList<>();
		for (int x=box.edgeLeftTop.x+1;x<scene.getDimension().getWidth();x++){
			Point current = scene.getPoint(x,y);
			if (current==null){
				return null;
			}
			if (box.wouldBePossibleLinePart(current)) {
				points.add(current);
				continue;
			}
			if (box.wouldBePossibleEdge(current)){
				box.edgeRightTop=current;
				box.lines.add(new Line(points));
				break;
			}
		}
		return box;
	}
	
	private static Box fromRightTopToRightBottom(Scene scene, Box box) {
		int x=box.edgeRightTop.x;
		
		List<Point> points = new ArrayList<>();
		for (int y= box.edgeRightTop.y+1;y<scene.getDimension().getHeight();y++){
			Point current = scene.getPoint(x,y);
			if (current==null){
				return null;
			}
			if (box.wouldBePossibleLinePart(current)) {
				points.add(current);
				continue;
			}
			if (box.wouldBePossibleEdge(current)){
				box.edgeRightBottom=current;
				box.lines.add(new Line(points));
				break;
			}
		}
		return box;
	}
	
	private static Box fromRightBottomToLeftBottom(Scene scene, Box box) {
		int y= box.edgeRightBottom.y;
		List<Point> points = new ArrayList<>();
		for (int x=box.edgeRightBottom.x-1;x>=0;x--){
			Point current = scene.getPoint(x,y);
			if (current==null){
				return null;
			}
			if (box.wouldBePossibleLinePart(current)) {
				points.add(current);
				continue;
			}
			if (box.wouldBePossibleEdge(current)){
				box.edgeLeftBottom=current;
				box.lines.add(new Line(points));
				break;
			}
		}
		return box;
	}

	private static Box fromLeftBottomToLeftTop(Scene scene, Box box) {
		int x= box.edgeLeftBottom.x;
		List<Point> points = new ArrayList<>();
		for (int y=box.edgeLeftBottom.y-1;y>=0;y--){
			Point current = scene.getPoint(x,y);
			if (current==null){
				return null;
			}
			if (box.wouldBePossibleLinePart(current)) {
				points.add(current);
				continue;
			}
			if (box.wouldBePossibleEdge(current)){
				box.edgeLeftTop=current;
				box.lines.add(new Line(points));
				break;
			}
		}
		return box;
	}
	
	private boolean wouldBePossibleLinePart(Point current) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean wouldBePossibleEdge(Point point) {
		/* TODO Albert: box position should be also investigated...*/
		switch (point.character){
			case '+':
			case '/':
			case '\\':
				return true;	
			default:
				return false;
		}
	}

	public List<Point> getPoints() {
		List<Point> points = new ArrayList<>();
		for (Line line: lines){
			points.addAll(line.getPoints());
		}
		return points;
	}
}
