package Character;
import org.newdawn.slick.*;

import Maps.Maps;
import Piege.ListePique;


public class Character {
	//creation de toutes les variables
	private float img_caseX = 0;
	private float img_caseY = 0;
	private float vertical_speed;
	private final float GRAVITY = 1f;
	private final float TERMINAL_VELOCITY = 7;
	private Image toast;
	private Animation idle_LEFT;
	private Animation idle_RIGHT;
	private Animation run_RIGHT;
	private Animation run_LEFT;
	private Animation jump_RIGHT;
	private Animation jump_LEFT;
	private Animation fall_RIGHT;
	private Animation fall_LEFT;
	private Direction direction;
	private Direction AncienneDirection;
	private IA ia;
	
	private static class Singleton{
		private static Character INSTANCE = new Character();
	}
	public static Character getInstance() {
		return Singleton.INSTANCE;
	}
	//getter et setter des coordonn�es du personnage
	public float getImg_caseX() {
		return img_caseX;
	}

	public void setImg_caseX(float img_caseX) {
		this.img_caseX = img_caseX;
	}

	public float getImg_caseY() {
		return img_caseY;
	}

	public void setImg_caseY(float img_caseY) {
		this.img_caseY = img_caseY;
	}
	
	public Character() {
		super();
		ia = IA.getInstance();
	}
	public void renderCharacter(GameContainer gc, Graphics grphcs) {
		
		float posX = img_caseX;
		float posY = img_caseY;
		//en fonction des direction on dessine la direction du personnage
		switch(direction) {
		case RUNRIGHT: run_RIGHT.draw(posX, posY);
			break;
		case RUNLEFT: run_LEFT.draw(posX, posY);
			break;
		case idleLEFT: idle_LEFT.draw(posX, posY);
			break;
		case idleRIGHT: idle_RIGHT.draw(posX, posY);
			break;
		case JUMPRIGHT: jump_RIGHT.draw(posX, posY);
			break;
		case JUMPLEFT: jump_LEFT.draw(posX, posY);
			break;
		case FALLRIGHT: fall_RIGHT.draw(posX, posY);
			break;
		case FALLLEFT: fall_LEFT.draw(posX, posY);
			break;
		}
	}
	//initialisation de toute les variables
	public void initCharacter(GameContainer gc) {
		try {
			toast = new Image("./sprites/charac.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		img_caseX = 750;
		img_caseY = 300;
		vertical_speed = 0;
		run_RIGHT = getAnimationRight(0, 6, 3);
		jump_RIGHT = getAnimationRight(0, 3 ,2);
		fall_RIGHT = getAnimationRight(0, 3, 0);
		idle_RIGHT = getAnimationRight(0, 1, 1);
		run_LEFT = getAnimationLeft(5, 0, 8);
		jump_LEFT = getAnimationLeft(5, 3, 7);
		fall_LEFT = getAnimationLeft(5, 3, 5);
		idle_LEFT = getAnimationLeft(5, 4, 6);
		fall_RIGHT = getAnimationRight(0, 2, 0);		
		fall_LEFT = getAnimationLeft(5, 3, 5);
		direction = Direction.RUNLEFT;
		AncienneDirection = direction;
	}
	//methode pour get une animation sur une spritesheet
	private Animation getAnimationLeft(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x > max; x--) {
			anim.addFrame(toast.getSubImage(x*32, rowY*64, 32, 64), 50);
		}
		return anim;
	}
	private Animation getAnimationRight(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(toast.getSubImage(x*32, rowY*64, 32, 64), 50);
		}
		return anim;
	}
	
	public void updateCharacter(GameContainer gc, int i, Maps map, ListePique pique) {
		Input input = gc.getInput();
		//si le perso va a droite ou a gauche et qu'il y a un mur il est repouss� dans la direction oppos�
		if(!map.isGrounded((int)(img_caseX), (int)(img_caseY+50), "Sol") && AncienneDirection == Direction.RUNLEFT) { 
			img_caseX += 6;
		}
		if(!map.isGrounded((int)(img_caseX+32), (int)(img_caseY+50), "Sol") && AncienneDirection == Direction.RUNRIGHT) {
			img_caseX -= 6;
		}
		//si la touche A est enfonc� et qu'il n'y a pas de mur il peut avancer
		if(input.isKeyDown(Input.KEY_A) && map.isGrounded((int)(img_caseX-5), (int)(img_caseY), "Sol")) {
			direction = Direction.RUNLEFT;
			AncienneDirection = direction;
			img_caseX -= 4;
			run_LEFT.update((long) (i/2.5));
		}
		//sinon il reste sur place
		
		else{
			if(AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
			}
		}
		//si la touche D est enfonc� et qu'il n'y a pas de mur
		if(input.isKeyDown(Input.KEY_D) && map.isGrounded((int)(img_caseX+32), (int)(img_caseY), "Sol")) {
			direction = Direction.RUNRIGHT;
			AncienneDirection = direction;
			img_caseX += 4;
			run_RIGHT.update((long) (i/2.5));
		}
		//sinon il reste sur place
		else {
			if(AncienneDirection == Direction.RUNRIGHT) {
				direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));
			}
		}
		//si la barre espace est enfonc� il saute en fonction de l'ancienne direction
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.JUMPLEFT;
				img_caseY -= 7;
				jump_LEFT.update((long) (i/2.5));
			}
			else if(AncienneDirection == Direction.RUNRIGHT) {
				direction = Direction.JUMPRIGHT;
				img_caseY -= 7;
				jump_RIGHT.update((long) (i/2.5));
			}
		}
		//sinon si le perso est dans les airs il retombe avec tourn�e dans l'ancienne direction du perso (gravit�)
		else if(map.isGrounded((int)(img_caseX), (int)(img_caseY+64), "Sol") && map.isGrounded((int)(img_caseX), (int)(img_caseY+64), "Obstacles") ) {
			this.vertical_speed = (int) (vertical_speed + GRAVITY);
	        if (vertical_speed > TERMINAL_VELOCITY)
	        {
	            vertical_speed = TERMINAL_VELOCITY;
	        }
	        img_caseY = img_caseY + vertical_speed;
			if(AncienneDirection == Direction.RUNRIGHT) {
				direction = Direction.FALLRIGHT;
				fall_RIGHT.update((long) (i/2.5));
			}
			if(AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.FALLLEFT;
				fall_LEFT.update((long) (i/2.5));
			}
        	
		}
	}
}