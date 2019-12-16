package Character;
import org.newdawn.slick.*;

import Maps.Maps;

enum Direction{
	idleRIGHT, idleLEFT, RUNRIGHT, RUNLEFT, JUMPRIGHT, JUMPLEFT, LEFTKNIFE, RIGHTKNIFE, FALLRIGHT, FALLLEFT
}

public class Compagnon {
	private int img_caseX;
	private int img_caseY;
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
	
	public Compagnon() {
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
		case FALLRIGHT: fall_RIGHT.draw(posX, posY);
			break;
		case FALLLEFT: fall_LEFT.draw(posX, posY);
			break;
		}
	}
	
	public void initCharacter(GameContainer gc) {
		try {
			toast = new Image("./sprites/toast.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img_caseX = 3;
		img_caseY = 300;
		idle_LEFT = getAnimation(0, 2, 0);
		idle_RIGHT = getAnimation(9, 11, 4);
		run_RIGHT = getAnimation(7, 11 ,5);
		run_LEFT = getAnimation(0, 4, 1);
		jump_RIGHT = getAnimationJump(10, 3, 6);
		jump_LEFT = getAnimation(1, 7, 2);
		direction = Direction.RUNLEFT;
		AncienneDirection = direction;
	}
	
	private Animation getAnimation(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(toast.getSubImage(x*64, rowY*64, 64, 64), 50);
		}
		return anim;
	}
	
	private Animation getAnimationJump(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x > max; x--) {
			anim.addFrame(toast.getSubImage(x*64, rowY*64, 64, 64), 50);
		}
		return anim;
	}
	
	public void updateCharacter(GameContainer gc, int i, Maps map) {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.RUNLEFT;
			img_caseX -= 2;
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
			img_caseX += 2;
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