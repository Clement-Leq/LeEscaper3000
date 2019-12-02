package Maps;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps {
	private TiledMap map;
	
	public boolean isGrounded(int x, int y,String calque) {
			return map.getTileId(x/32, y/32, map.getLayerIndex(calque)) == 0;
	}
	
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
	public void updateMap(){
		
	}
}
