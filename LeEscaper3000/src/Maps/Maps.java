package Maps;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps {
	private TiledMap map;
	
	public void iniMap() {
		try {
			map = new TiledMap("./maps/Java_Projet.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void renderMap() {
		map.render(0,0);
	}
}
