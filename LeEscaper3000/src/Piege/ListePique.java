package Piege;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Maps.Maps;

public class ListePique {
	//liste des piques
	ArrayList<Pique> arrayPiques;
	private Pique pique;
	//creation de la liste
	public ListePique() {
		arrayPiques = new ArrayList<Pique>();
	}
	//Rectangle pour definir la hitbox des piques de la liste
	public Rectangle getListRec() {
		for (int i = 0; i < arrayPiques.size(); i++) {
			arrayPiques.get(i).getRectanglePique();
		}
		return null;
	}
	//genere tous les piques a des position differente
	public void genere() {
		int[] valeurs = { 10, 35, 58, 90, 140, 190, 220, 260, 298, 299 };
		
		for (int i = 0; i < 9; i++) {
			int x = valeurs[i];
			pique = new Pique(x*32);
			arrayPiques.add(pique);
		}
	}
	//rendu de la liste de pique
	public void renderListe(GameContainer gc, Graphics grphcs) {
		// piques.forEach(element->element.renderPique(gc, grphcs));
		for (int i = 0; i < arrayPiques.size(); i++) {
			arrayPiques.get(i).renderPique(gc, grphcs);
		}
	}
	//initialisation de la liste de pique
	public void initListe(GameContainer gc) {
		// piques.forEach(element->element.initPique(gc));
		this.genere(); // creation des piques
		// puis on les initialise
		for (int i = 0; i < arrayPiques.size(); i++) {
			arrayPiques.get(i).initPique(gc);
		}
	}
	//update de la liste de pique
	public void updateListe(GameContainer gc, int i, Maps map) {
		// piques.forEach(element->element.updatePique(gc, i, map));
		for (int j = 0; j < arrayPiques.size(); j++) {
			arrayPiques.get(j).updatePique(gc, i, map);
		}
	}
}
