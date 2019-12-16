package Piege;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import Maps.Maps;

public class Pique {
	//creation des variables
	private float caseX;
	private float caseY;
	private float vertical_speed;
	private final float GRAVITY = 1f;
	private final float TERMINAL_VELOCITY = 7;
	private Image pique;
	private Animation q;
	
	//constructeur des piques avec une coordonée en X
	public Pique(int img_caseX) {
		super();
		this.caseX = img_caseX;
		this.caseY = 0;
	}
	//crée le rectangle pour la hitbox des piques
	public Rectangle getRectanglePique() {
        float PersoPosX = caseX;
        float PersoPosY = caseY;
        float PersoTailleX = 32;
        float PersoTailleY = 32;
        return new Rectangle(PersoPosX, PersoPosY, PersoTailleX, PersoTailleY);
    }
	//render des piques
	public void renderPique(GameContainer gc, Graphics grphcs){
		q.draw(caseX, caseY);
	}
	//initialisation des piques
	public void initPique(GameContainer gc){
		try {
			pique = new Image("./sprites/pique.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		q = getAnimation();
		vertical_speed = 0;
	}
	//get l'animation des piques
	private Animation getAnimation() {
		Animation anim = new Animation(false);
			anim.addFrame(pique.getSubImage(0*32, 0*32, 32, 32), 50);
		return anim;
	}
	//gravité sur le piques pour les mettres sur le sol
	public void updatePique(GameContainer gc, int i, Maps map){
		if(map.isGrounded((int)(caseX), (int)(caseY+32), "Sol") && map.isGrounded((int)(caseX), (int)(caseY+32), "Obstacles") ) {
			this.vertical_speed = (int) (vertical_speed + GRAVITY);
	        if (vertical_speed > TERMINAL_VELOCITY)
	        {
	            vertical_speed = TERMINAL_VELOCITY;
	        }
	        caseY = caseY + vertical_speed;
		}
	}
}
