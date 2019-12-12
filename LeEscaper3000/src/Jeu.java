import org.newdawn.slick.*;
import Character.Character;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		personnage = new Character();
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
		personnage.updateCharacter(gc, i, map);
	}
}