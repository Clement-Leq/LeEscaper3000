import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Character.Character;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	
	public Jeu(String title) {
		super(title);
		personnage = new Character();
		map = new Maps();
	}

	public void render(GameContainer gc, Graphics grphcs){
		map.renderMap();
		personnage.renderCharacter(gc, grphcs);
	}

	public void init(GameContainer gc){
		map.iniMap();
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i){
		personnage.updateCharacter(gc, i);				
	}

}
