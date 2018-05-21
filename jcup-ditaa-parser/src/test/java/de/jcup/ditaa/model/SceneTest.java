package de.jcup.ditaa.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.jcup.ditaa.TestFileLoader;

public class SceneTest {

	@Before
	public void before(){
	}
	
	@Test
	public void simple_box_ditaa_file_string_to_scene_contains_endpoints_with_correct_coordinates() throws Exception{
		/* prepare */ 
		String simpleBox = TestFileLoader.loadScriptFromTestScripts("a_simple_box.ditaa");
	
		/* execute */
		Scene scene = Scene.fromString(simpleBox);
		String dump = scene.print(true);
		System.out.println(dump);
		/* test */
		assertNotNull(scene);
		
		assertEquals(new Point(0,0,'+'), scene.getPoint(0,0));
		assertEquals(new Point(0,6,'+'), scene.getPoint(0,6));
		assertEquals(new Point(18,0,'+'), scene.getPoint(18,0));
		assertEquals(new Point(18,6,'+'), scene.getPoint(18,6));
		
	}
	
	@Test
	public void simple_box_with_spacing_5_ditaa_file_string_to_scene_contains_endpoints_with_correct_coordinates() throws Exception{
		/* prepare */ 
		String simpleBox = TestFileLoader.loadScriptFromTestScripts("a_simple_box_with_spacing_5.ditaa");
	
		/* execute */
		Scene scene = Scene.fromString(simpleBox);
		String dump = scene.print(true);
		System.out.println(dump);
		/* test */
		assertNotNull(scene);
		
		assertEquals(new Point(5,0,'+'), scene.getPoint(5,0));
		assertEquals(new Point(5,6,'+'), scene.getPoint(5,6));
		assertEquals(new Point(23,0,'+'), scene.getPoint(23,0));
		assertEquals(new Point(23,6,'+'), scene.getPoint(23,6));
		
	}

}
