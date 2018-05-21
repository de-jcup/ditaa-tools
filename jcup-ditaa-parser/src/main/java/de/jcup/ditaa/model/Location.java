package de.jcup.ditaa.model;

public class Location {

	protected int x;
	protected int y;

	public Location(int x, int y) {
		if (x<0){
			throw new IllegalArgumentException("x may not be smaller 0:"+x);
		}
		if (y<0){
			throw new IllegalArgumentException("y may not be smaller 0:"+y);
		}
		this.x=x;
		this.y=y;
	}

}