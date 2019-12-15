package Piege;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import Maps.Maps;



public class ListePique {
	
	ArrayList<Pique> piques;
	private UsinePiege usine= new UsinePiege();
	
	public void genere(){
		Pique pique = null;
		int x = 0;
		for(int i = 0; i>9; i++) {
			switch (i) {
			case 0: x = 10;
					break;
			case 1: x = 35;
					break;
			case 2: x = 58;
					break;
			case 3: x = 90;
					break;
			case 4: x = 140;
					break;
			case 5: x = 190;
					break;
			case 6: x = 220;
					break;
			case 7: x = 260;
					break;
			case 8: x = 298;
					break;
			case 9: x = 299;
					break;	
			}
			pique = usine.getPique(x);
			piques.add(pique);
		}
	}
	public void renderListe(GameContainer gc, Graphics grphcs){
		piques.forEach(element->element.renderPique(gc, grphcs));
	}

	public void initListe(GameContainer gc){
		piques.forEach(element->element.initPique(gc));
	}
	
	public void updateListe(GameContainer gc, int i, Maps map){
		piques.forEach(element->element.updatePique(gc, i, map));
	}
}
