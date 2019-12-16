import org.newdawn.slick.*;
import Character.Character;
import Maps.Maps;
import Piege.ListePique;
import Piege.Pique;
 

public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	private ListePique listePique;
	private Pique piques;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		personnage = new Character();
		listePique = new ListePique();
	}

	public void render(GameContainer gc, Graphics grphcs){
		grphcs.translate(-personnage.getImg_caseX()+400, 0);
		map.renderMap();
		listePique.renderListe(gc, grphcs); 
		personnage.renderCharacter(gc, grphcs);
	}
	
	public void init(GameContainer gc  ){
		map.iniMap();
		listePique.initListe(gc);
		personnage.initCharacter(gc);
	}
	
	public void update(GameContainer gc, int i){
		listePique.updateListe(gc, i, map);
		personnage.updateCharacter(gc, i, map, listePique);
	}
}