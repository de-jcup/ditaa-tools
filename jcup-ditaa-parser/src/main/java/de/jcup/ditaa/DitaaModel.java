package de.jcup.ditaa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.jcup.ditaa.model.Box;

public class DitaaModel {

	private List<Box> boxes = new ArrayList<>();
	
	public List<Box> getBoxes() {
		return Collections.unmodifiableList(boxes);
	}

	public void add(Box box) {
		boxes.add(box);
	}

}
