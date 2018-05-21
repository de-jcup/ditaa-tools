package de.jcup.ditaa;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.jcup.ditaa.model.Box;
import de.jcup.ditaa.model.Line;
import de.jcup.ditaa.model.Point;
import de.jcup.ditaa.model.Scene;
import de.jcup.ditaa.model.Text;

public class DitaaParserTest {

	private DitaaParser parserToTest;

	@Before
	public void before(){
		parserToTest = new DitaaParser();
	}
	
	@Test
	public void test_empty_string_returns_model_not_null() throws Exception{
	
		/* execute */
		DitaaModel parsed = parserToTest.parse("");
		
		/* test */
		assertNotNull(parsed);
		 
	}
	
	@Test
	public void simple_box_ditaa_file_is_parsed_as_one_box_with_correct_coordinates() throws Exception{
		/* prepare */ 
		String simpleBox = TestFileLoader.loadScriptFromTestScripts("a_simple_box.ditaa");
	
		/* execute */
		DitaaModel model = parserToTest.parse(simpleBox);
		
		/* test */
		assertNotNull(model);
		List<Box> boxes = model.getBoxes(); 
		assertNotNull(boxes);
		assertEquals(1,boxes.size());
		
		Box box = boxes.iterator().next();
		assertEquals(new Point(0,0,'+'), box.getEdgeLeftTop());
		assertEquals(new Point(0,6,'+'), box.getEdgeLeftBottom());
		assertEquals(new Point(18,0,'+'), box.getEdgeRightTop());
		assertEquals(new Point(18,6,'+'), box.getEdgeRightBottom());
		
	}
	
	@Test
	public void buildBoxesAreParseCorrectly(){
		/* prepare */
		Box box = new Box(new Point(3,8),20,8);
		
		Box box2 = new Box(new Point(43,5),20,10);
		
		Scene scene = new Scene();
		box.draw(scene);
		box2.draw(scene);
		
		String result = scene.print();
		System.out.println(result);
		
		/* execute */
		DitaaModel model = parserToTest.parse(result);
		
		/* test */
		List<Box> boxes = model.getBoxes();
		assertEquals(2,boxes.size());
		
		Iterator<Box> iterator = boxes.iterator();
		Box rbox1 = iterator.next();
		Box rbox2 = iterator.next();
		assertEquals(new Point(3,8), rbox1.getEdgeLeftTop());
		assertEquals(new Point(43,5), rbox2.getEdgeLeftTop());
		
		Scene scene2 = new Scene();
		rbox1.draw(scene2);
		rbox2.draw(scene2);
		System.out.println("--");
		String result2 = scene.print();
		System.out.println(result2);
		assertEquals(result,result2);
	}

}
