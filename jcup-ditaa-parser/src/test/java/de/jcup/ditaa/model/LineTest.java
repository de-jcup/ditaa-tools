package de.jcup.ditaa.model;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LineTest {
	
//	@Rule
//	public Timeout timeOut = Timeout.millis(30);

	@Test
	public void line_from_0_0__to__0_2() {
		/* execute */
		Line line = new Line(new Point(0,0),new Point(0,2));

		/* test */
		dumpLIne(line);
		assertTrue(line.linePoints.contains(new Point(0,1,'|')));
		assertEquals(3, line.linePoints.size());
		
	}
	
	@Test
	public void line_from_0_0__to__2_0() {
		Line line = new Line(new Point(0,0),new Point(2,0));
		
		/* test */
		dumpLIne(line);
		assertTrue(line.linePoints.contains(new Point(1,0,'-')));
		assertEquals(3, line.linePoints.size());
	}
	
	@Test
	public void line_from_2_0__to__0_0() {
		Line line = new Line(new Point(2,0),new Point(0,0));
		
		/* test */
		dumpLIne(line);
		assertTrue(line.linePoints.contains(new Point(1,0,'-')));
		assertEquals(3, line.linePoints.size());
	}
	
	@Test
	public void line_from_0_2__to__0_0() {
		Line line = new Line(new Point(0,2),new Point(0,0));
		
		/* test */
		dumpLIne(line);
		assertTrue(line.linePoints.contains(new Point(0,1,'|')));
		assertEquals(3, line.linePoints.size());
	}

	protected void dumpLIne(Line line) {
		System.out.println("Line:");
		for (Point p : line.linePoints){
			System.out.println(p);
		}
		System.out.println("---");
		Scene scene = new Scene();
		line.draw(scene);
		String result = scene.print();
		System.out.println(result);
	}
}
