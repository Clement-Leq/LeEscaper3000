package Maps;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps {
	private TiledMap map;
	//retourne true si l'ID du calque de la map egal a 0
	public boolean isGrounded(int x, int y,String calque) {
			return map.getTileId(x/32, y/32, map.getLayerIndex(calque)) == 0;
	}
	//recupere la tiledmap
	public void iniMap() {
		try {
			map = new TiledMap("./maps/Java_Projet.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	//rendu de la map
	public void renderMap() {
		map.render(0,0);
	}
	
	public void updateMap(){
		
	}
}
