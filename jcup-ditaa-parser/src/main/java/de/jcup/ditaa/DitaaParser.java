package de.jcup.ditaa;

import de.jcup.ditaa.model.Box;
import de.jcup.ditaa.model.Dimension;
import de.jcup.ditaa.model.Location;
import de.jcup.ditaa.model.Scene;

public class DitaaParser {

	public DitaaModel parse(String text) {
		DitaaModel model = new DitaaModel();
		if (text==null || text.trim().isEmpty()){
			return model;
		}
		Scene scene = Scene.fromString(text);
		
		fetchBoxesAndRemoveFromScene(model,scene);
		
		
		return model;
		
	}

	private void fetchBoxesAndRemoveFromScene(DitaaModel model, Scene scene) {
		Dimension dimension = scene.getDimension();
		for (int x=0;x<dimension.getWidth();x++){
			for (int y=0;y<dimension.getHeight();y++){
				Box box = Box.from(scene, new Location(x,y));
				if (box!=null){
					model.add(box);
					scene.remove(box.getPoints());
				}
			}
		}
		
	}

}
