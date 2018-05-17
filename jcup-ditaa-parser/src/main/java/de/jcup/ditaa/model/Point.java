package de.jcup.ditaa.model;

public class Point implements Shape{
	int x;
	int y;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	Character character;
	
	public Point(int x, int y) {
		this(x,y,'+');
	}
	public Point(int x, int y, char c) {
		if (x<0){
			throw new IllegalArgumentException("x may not be smaller 0:"+x);
		}
		if (y<0){
			throw new IllegalArgumentException("y may not be smaller 0:"+y);
		}
		this.x=x;
		this.y=y;
		this.character=Character.valueOf(c);
	}

	public void draw(Scene targetScene) {
		targetScene.add(this);
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", character=" + character + "]";
	}
	
	
}