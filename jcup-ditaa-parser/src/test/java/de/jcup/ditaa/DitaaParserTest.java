package de.jcup.ditaa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.jcup.ditaa.model.Box;
import de.jcup.ditaa.model.Point;

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

}
