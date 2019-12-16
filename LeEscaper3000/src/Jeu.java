import org.newdawn.slick.*;
import Character.Character;
import Maps.Maps;
import Piege.ListePique;
 

public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	private ListePique listePique;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		personnage = new Character();
		listePique = new ListePique();
	}

	public void render(GameContainer gc, Graphics grphcs){
		grphcs.translate(-personnage.getImg_caseX()+400, 0); // camera qui suit le personnage avec 400px entre le botd gauche et le personnage
		map.renderMap(); //rendu de la map
		listePique.renderListe(gc, grphcs); //rendu des piques
		personnage.renderCharacter(gc, grphcs); //rendu du personnage
	}
	
	public void init(GameContainer gc  ){
		map.iniMap(); //initailisation de la map
		listePique.initListe(gc); //initailisation des piques
		personnage.initCharacter(gc); //initailisation du personnage
	}
	
	public void update(GameContainer gc, int i){
		listePique.updateListe(gc, i, map); //update des piques
		personnage.updateCharacter(gc, i, map, listePique); //update du personnage
	}
}