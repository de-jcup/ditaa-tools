package de.jcup.ditaa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Line extends AbstractShape {

	List<Point> linePoints;
	private Point leftTop;
	
	public Line(List<Point> points) {
		init(points);
	}
	public Line(Point... points) {
		List<Point> asList = Arrays.asList(points);
		init(asList);
	}
	
	private void init(List<Point> asList) {
		Iterator<Point> it = asList.iterator();
		this.linePoints=new ArrayList<>();
		
		Location beforePointFromLineList = null;
		while (it.hasNext()){
			Point target = it.next();
			handleLeftTop(target);
			if (beforePointFromLineList!=null){
				Location pos = new Point(beforePointFromLineList.x,beforePointFromLineList.y);
				while (notSamePosition(pos, target)){
					Point nextPos = createNextPointToMoveOn(target, pos);
					if (notSamePosition(nextPos, target)){
						linePoints.add(nextPos);
						handleLeftTop(nextPos);
					}
					pos = nextPos;
				}
			}
			linePoints.add(target);
			beforePointFromLineList=target;
			
		}
	}
	
	private void handleLeftTop(Point point){
		if (point==null){
			return;
		}
		if (leftTop==null){
			leftTop=point;
			return;
		}
		if (leftTop.x==point.x){
			if (leftTop.y>point.y){
				leftTop=point;
				return;
			}
		}
		
		if (leftTop.x<point.x){
			leftTop=point;
			return;
		}
	}
	
	@Override
	public Point getLeftTop() {
		return leftTop;
	}
	
	public Set<Point> getPoints() {
		return new LinkedHashSet<>(linePoints);
	}

	protected Point createNextPointToMoveOn(Location target, Location pos) {
		boolean moveNextRight=target.x>pos.x;
		boolean moveNextLeft=target.x<pos.x;
		
		boolean moveNextDown=target.y>pos.y;
		boolean moveNextUp=target.y<pos.y;
		
		/* only up /down when not already a left or right*/
		moveNextDown=moveNextDown&& (! (moveNextLeft || moveNextRight));
		moveNextUp=moveNextUp&& (! (moveNextLeft || moveNextRight));
		
		Point nextPos = new Point(pos.x,pos.y);
		if (moveNextRight){
			nextPos.x++;
		}else if (moveNextLeft){
			nextPos.x--;
		}
		if (moveNextDown){
			nextPos.y++;
		}else if (moveNextUp){
			nextPos.y--;
		}
		
		
		char c;
		if (moveNextRight){
			if (moveNextDown){
				c='\\';
			}else if (moveNextUp){
				c='/';
			}else{
				c='-';
			}
		}else if (moveNextLeft){
			if (moveNextDown){
				c='/';
			}else if (moveNextUp){
				c='\\';
			}else{
				c='-';
			}
		}else{
			c='|'; /* up or down, does not matter*/
		}
		nextPos.character=c;
		return nextPos;
	}

	protected boolean notSamePosition(Location lastPoint, Location point) {
		return lastPoint.x!=point.x || lastPoint.y!=point.y;
	}

	@Override
	public void draw(Scene targetScene) {
		for (Point point : linePoints) {
			point.draw(targetScene);
		}
	}
	
}
