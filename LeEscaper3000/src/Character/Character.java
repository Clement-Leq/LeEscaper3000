package Character;
import org.newdawn.slick.*;

import Maps.Maps;

enum Direction{
	idleRIGHT, idleLEFT, RUNRIGHT, RUNLEFT, JUMPRIGHT, JUMPLEFT, FALLRIGHT, FALLLEFT
}

public class Character {
	private int img_caseX;
	private int img_caseY;
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
	
	public int getImg_caseX() {
		return img_caseX;
	}

	public void setImg_caseX(int img_caseX) {
		this.img_caseX = img_caseX;
	}

	public int getImg_caseY() {
		return img_caseY;
	}

	public void setImg_caseY(int img_caseY) {
		this.img_caseY = img_caseY;
	}
	
	public Character() {
		super();
	}
	public void renderCharacter(GameContainer gc, Graphics grphcs) {
		
		int posX = img_caseX;
		int posY = img_caseY;
		
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
		default:
			break;
		}
	}
	public void initCharacter(GameContainer gc) {
		try {
			toast = new Image("./sprites/charac.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		img_caseX = 3;
		img_caseY = 300;
		run_RIGHT = getAnimationRight(0, 6, 3);
		jump_RIGHT = getAnimationRight(0, 3 ,2);
		fall_RIGHT = getAnimationRight(0, 3, 0);
		idle_RIGHT = getAnimationRight(0, 1, 1);
		run_LEFT = getAnimationLeft(5, 0, 8);
		jump_LEFT = getAnimationLeft(5, 3, 7);
		fall_LEFT = getAnimationLeft(5, 3, 5);
		idle_LEFT = getAnimationLeft(5, 4, 6);
		direction = Direction.RUNLEFT;
		AncienneDirection = direction;
	}
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
	
	public void updateCharacter(GameContainer gc, int i, Maps map) {
		Input input = gc.getInput();
		
		if(map.isGrounded(img_caseX, img_caseY+64, "Sol")) {
			img_caseY += 4;
		}
		/*if(personnage.getLayoutY() + personnage.getFitHeight() <= background.getFitHeight() * 0.8d){
			HGY.set(HGY.get()  + vitesseY);
			vitesseY += g; 
		}
	
		if(personnage.getLayoutY() + personnage.getFitHeight() > background.getFitHeight() * 0.8d){
			HGY.set((background.getFitHeight() * 0.8d - personnage.getFitHeight()) / background.getFitHeight());
			vitesseY = 0;
		}*/
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.RUNLEFT;
			img_caseX -= 4;
			run_LEFT.update((long) (i/2.5));
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
			}
		}
		if(input.isKeyDown(Input.KEY_D)) {
			direction = Direction.RUNRIGHT;
			img_caseX += 4;
			run_RIGHT.update((long) (i/2.5));
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.RUNRIGHT) {
				direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));
			}
		}
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.JUMPLEFT;
				img_caseY -= 10;
				jump_LEFT.update((long) (i/2.5));
			}
			else if(AncienneDirection == Direction.RUNRIGHT) {
				direction = Direction.JUMPRIGHT;
				img_caseY -= 10 ;
				jump_RIGHT.update((long) (i/2.5));
			}
		}
	}
}
