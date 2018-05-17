package de.jcup.ditaa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Line implements Shape {

	List<Point> linePoints;

	public Line(Point... points) {
		List<Point> asList = Arrays.asList(points);
		Iterator<Point> it = asList.iterator();
		this.linePoints=new ArrayList<>();
		
		Point beforePointFromLineList = null;
		while (it.hasNext()){
			Point target = it.next();
			
			if (beforePointFromLineList!=null){
				Point pos = new Point(beforePointFromLineList.x,beforePointFromLineList.y);
				while (notSamePosition(pos, target)){
					Point nextPos = createNextPointToMoveOn(target, pos);
					if (notSamePosition(nextPos, target)){
						linePoints.add(nextPos);
					}
					pos = nextPos;
				}
			}
			linePoints.add(target);
			beforePointFromLineList=target;
			
		}
	}

	protected Point createNextPointToMoveOn(Point target, Point pos) {
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

	protected boolean notSamePosition(Point lastPoint, Point point) {
		return lastPoint.x!=point.x || lastPoint.y!=point.y;
	}

	@Override
	public void draw(Scene targetScene) {
		for (Point point : linePoints) {
			point.draw(targetScene);
		}

	}
}
