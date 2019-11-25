import org.newdawn.slick.*;

import Character.Character;
import Character.Gardien;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	private Gardien mechantMur;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		mechantMur = new Gardien();
		personnage = new Character();
	}

	public void render(GameContainer gc, Graphics grphcs){
		map.renderMap();
		mechantMur.renderGardien(gc, grphcs);
		personnage.renderCharacter(gc, grphcs);
	}

	public void init(GameContainer gc){
		map.iniMap();
		mechantMur.initGardien(gc);
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i){
		mechantMur.updateGardien(gc, i);
		personnage.updateCharacter(gc, i, map);
	}
}