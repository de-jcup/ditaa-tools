package de.jcup.ditaa.model;

public abstract class AbstractShape implements Shape{

	@Override
	public void move(Direction direction) {
		if (! canMove(direction)){
			return;
		}
		moveImpl(direction);
	}
	
	/**
	 * Implementation of movement. The standard check for not becoming negatie coordinates is already done
	 * @param direction
	 */
	protected void moveImpl(Direction direction) {
		for (Point point : getPoints()) {
			moveLocation(direction, point);
		}
	}

	private void moveLocation(Direction direction, Location point) {
		switch (direction) {
		case UP:
			point.y--;
			break;
		case DOWN:
			point.y++;
			break;
		case LEFT:
			point.x--;
			break;
		case RIGHT:
			point.x++;
			break;
		}
	}
	
	private boolean canMove(Direction direction){
		Location leftTop = getLeftTop();
		if (leftTop == null) {
			return false;
		}
		switch (direction) {
		case LEFT:
			if (leftTop.x <= 0) {
				return false;
			}
			return true;
		case RIGHT:
			return true;
		case UP:
			if (leftTop.y <= 0) {
				return false;
			}
			return true;
		case DOWN:
		default:
			return true;
		}
	}
	
}
