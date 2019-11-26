package Maps;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps {
	private TiledMap map;
	
	public int index(String calque) {
		return map.getLayerIndex(calque);
	}
	public boolean isGrounded(int x, int y,String calque) {
		if(map.getTileId(x/32, y/32, index(calque)) == 0) {
			return true;
		}
		else {
			return false;
		}
		
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