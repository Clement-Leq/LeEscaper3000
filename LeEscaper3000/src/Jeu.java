import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Character.Character;


public class Jeu extends BasicGame{
	private Character personnage;
	//private TiledMap map;
	
	public Jeu(String title) {
		super(title);
		personnage = new Character();
	}

	public void render(GameContainer gc, Graphics grphcs){
		//map.render(0, 0);
		personnage.renderCharacter(gc, grphcs);
	}

	public void init(GameContainer gc){
		/*try {
			map = new TiledMap("./maps/Java_Projet.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}*/
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i){
		personnage.updateCharacter(gc, i);				
	}

}
