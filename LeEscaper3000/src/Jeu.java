import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Character.Character;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private TiledMap map;
	private int tilesSize;
	
	public Jeu(String title) {
		super(title);
		personnage = new Character();
	}

	public void render(GameContainer gc, Graphics grphcs){
		map.render(0,0);
		personnage.renderCharacter(gc, grphcs);
	}

	public void init(GameContainer gc) throws SlickException{
		map = new TiledMap("./maps/Java_Projet.tmx");
		tilesSize = 32;
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i){
		personnage.updateCharacter(gc, i);				
	}

}