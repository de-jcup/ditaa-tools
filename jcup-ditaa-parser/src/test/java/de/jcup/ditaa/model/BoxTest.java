package de.jcup.ditaa.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoxTest {

	@Test
	public void testBuildAndMove() {
		Box box = new Box(new Point(3,8),20,8);
		
		Box box2 = new Box(new Point(43,5),20,10);
		
		Line line = new Line(new Point(23,10),new Point(42,10,'>'));
		Line line2 = new Line(new Point(23,11),new Point(42,15,'>'));
		
		Text text = new Text(new Point(5,7),"SCM trigger...");
		
		Text text2 = new Text(new Point(box2.getEdgeLeftTop().x+3,box2.getEdgeLeftTop().y+1),"Build server...");
		
		Scene scene = new Scene();
		box.draw(scene);
		box2.draw(scene);
		line.draw(scene);
		line2.draw(scene);
		text.draw(scene);
		text2.draw(scene);
		
		String result = scene.print();
		System.out.println(result);
		
		for (int i=0;i<4;i++){
			box2.move(Direction.DOWN);
			box2.move(Direction.RIGHT);
			line.move(Direction.RIGHT);
		}
		System.out.println("------------Move done-----------------");
		
		scene = new Scene();
		box.draw(scene);
		box2.draw(scene);
		line.draw(scene);
		line2.draw(scene);
		text.draw(scene);
		text2.draw(scene);
		result = scene.print();
		System.out.println(result);
		
	}

}
