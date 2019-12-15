package Piege;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import Maps.Maps;

public class Pique {
	private float caseX;
	private float caseY;
	private float vertical_speed;
	private final float GRAVITY = 1f;
	private final float TERMINAL_VELOCITY = 7;
	private Image pique;
	private Animation q;

	public Pique(int img_caseX) {
		super();
		this.caseX = img_caseX;
		this.caseY = 0;
	}


	
	public Rectangle getRectanglePerso() {
        float PersoPosX = caseX;
        float PersoPosY = caseY;
        float PersoTailleX = 32;
        float PersoTailleY = 32;
        return new Rectangle(PersoPosX, PersoPosY, PersoTailleX, PersoTailleY);
    }
    public void dessinerRectangles(Graphics grphcs) {
        grphcs.setColor(Color.white);
        Rectangle rectangleProjectile = getRectanglePerso();
        grphcs.draw(rectangleProjectile);
    }
	
	public void renderPique(GameContainer gc, Graphics grphcs){
		q.draw(caseX, caseY);
	}

	public void initPique(GameContainer gc){
		try {
			pique = new Image("./sprites/pique.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		q = getAnimation();
		vertical_speed = 0;
	}
	
	private Animation getAnimation() {
		Animation anim = new Animation(false);
			anim.addFrame(pique.getSubImage(0*32, 0*32, 32, 32), 50);
		return anim;
	}

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
