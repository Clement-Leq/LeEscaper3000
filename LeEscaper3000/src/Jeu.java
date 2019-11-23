import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Character.Character;

public class Jeu extends BasicGame{
	private Character personnage;
	
	public Jeu(String title) {
		super(title);
		personnage = new Character();
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
		personnage.renderCharacter(gc, grphcs);
		
	}

	public void init(GameContainer gc) throws SlickException {
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i) throws SlickException {
		personnage.updateCharacter(gc, i);				
	}

}
