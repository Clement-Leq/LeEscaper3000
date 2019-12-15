package Character;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Maps.Maps;

public class Pol_Cout {
	private int img_caseX;
	private int img_caseY;
	private Image gardienRight;
	private Image gardienLeft;
	private Animation anim_KNIFE_L;
	private Animation anim_KNIFE_R;
	private Animation idle_LEFT;
	private Animation idle_ARM_LEFT;
	private Animation idle_RIGHT;
	private Animation idle_ARM_RIGHT;
	private Animation anim_RIGHT;
	private Animation anim_ARM_R;
	private Animation anim_LEFT;
	private Animation anim_ARM_L;
	private Animation jump_RIGHT;
	private Animation jump_LEFT;
	private IA ia = IA.getInstance();
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

	public Pol_Cout() {
		super();
	}	
	
	private static class Singleton{
		private static Pol_Cout INSTANCE = new Pol_Cout();
	}
	public static Pol_Cout getInstance() {
		return Singleton.INSTANCE;
	}
	
	public void renderGardCout(GameContainer gc, Graphics grphcs) {
		int posX = img_caseX;
		int posY = img_caseY;
		
		switch(direction) {
		case RUNRIGHT: 
			anim_RIGHT.draw(posX, posY);
			anim_ARM_R.draw(posX+13, posY-5);
			break;
		case RUNLEFT: 
			anim_LEFT.draw(posX, posY);
			anim_ARM_L.draw(posX-13, posY-5);
			break;
		case idleLEFT: 
			idle_LEFT.draw(posX, posY);
			idle_ARM_LEFT.draw(posX-9, posY-2);
			break;
		case idleRIGHT: 
			idle_RIGHT.draw(posX, posY);
			idle_ARM_RIGHT.draw(posX+9, posY-2);
			break;
		case JUMPRIGHT: 
			jump_RIGHT.draw(posX, posY);
			break;
		case JUMPLEFT: 
			jump_LEFT.draw(posX, posY);
			break;
		case LEFTKNIFE:
			anim_KNIFE_L.draw(posX, posY);
			break;
		case RIGHTKNIFE:
			anim_KNIFE_R.draw(posX, posY);
			break;
		}
	}
	
	public void initGardCout(GameContainer gc) throws SlickException {
		gardienRight = new Image("./sprites/Police_couteau_right.png");
		gardienLeft = new Image("./sprites/Police_couteau_left.png");
		img_caseX = 610;
		img_caseY = 300;
		
		idle_RIGHT = getAnimationRight(0, 1, 0);
		idle_ARM_RIGHT = getAnimationRight(0, 1, 10);
		anim_RIGHT = getAnimationRight(0, 8, 3);
		anim_ARM_R = getAnimationRight(0, 8, 10);
		anim_KNIFE_R = getAnimationRight(25, 32, 0);
		jump_RIGHT = getAnimationJRight(22, 11, 6);
		
		idle_LEFT = getAnimationLeft(31, 30, 0); 
		idle_ARM_LEFT = getAnimationLeft(31, 30, 10);
		anim_LEFT = getAnimationLeft(31, 23, 3);
		anim_ARM_L = getAnimationLeft(31, 23, 10);
		anim_KNIFE_L = getAnimationLeft(6, 0, 0);
		jump_LEFT = getAnimationJLeft(9, 20, 6);
		
		direction = Direction.RUNLEFT;
		AncienneDirection = direction;
	}
	
	private Animation getAnimationRight(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(gardienRight.getSubImage(x*89, rowY*106, 80, 100), 50);
		}
		return anim;
	}
	private Animation getAnimationJRight(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x > max; x--) {
			anim.addFrame(gardienRight.getSubImage(x*89, rowY*106, 80, 100), 50);
		}
		return anim;
	}
	
	private Animation getAnimationLeft(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x > max; x--) {
			anim.addFrame(gardienLeft.getSubImage(x*89, rowY*106, 80, 100), 50);
		}
		return anim;
	}
	private Animation getAnimationJLeft(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(gardienLeft.getSubImage(x*89, rowY*106, 80, 100), 50);
		}
		return anim;
	}
	
	public void jump(GameContainer gc, int i, Maps map) {
		if(AncienneDirection == Direction.RUNLEFT || AncienneDirection == Direction.LEFTKNIFE) {
			direction = Direction.JUMPLEFT;
			img_caseY -= 15;
			jump_LEFT.update(i);
		}
		else if(AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE) {
			direction = Direction.JUMPRIGHT;
			img_caseY -= 15;
			jump_RIGHT.update(i);
		}
	}
	
	public void depDroite(GameContainer gc, int i) {
		//Input input = gc.getInput();
		 
		direction = Direction.RUNRIGHT; 
		img_caseX += 5;
		anim_RIGHT.update(i);
		anim_ARM_R.update(i);
		AncienneDirection = direction;

		/*if(AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE) {
			direction = Direction.idleRIGHT;
			idle_RIGHT.update((long) (i/2.5));
			idle_ARM_RIGHT.update((long) (i/2.5));
		}*/
	}
	
	public void depGauche(GameContainer gc, int i) {
		//Input input = gc.getInput();
		
		direction = Direction.RUNLEFT;
		img_caseX -= 5;
		anim_LEFT.update(i);
		anim_ARM_L.update(i);
		AncienneDirection = direction;
		
		/*
		else {
			if(AncienneDirection == Direction.LEFTKNIFE || AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
				idle_ARM_LEFT.update((long) (i/2.5));
			}
		}*/
	}
	
	public void couteau(GameContainer gc, int i) {
		if(AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE) {
			direction = Direction.RIGHTKNIFE;
			anim_KNIFE_R.update(i/2);
			AncienneDirection = direction;
		}
		else if (AncienneDirection == Direction.RUNLEFT || AncienneDirection == Direction.LEFTKNIFE) {
			direction = Direction.LEFTKNIFE;
			anim_KNIFE_L.update(i/2);
			AncienneDirection = direction;
		}
	}
	
	public void updateGardCout(GameContainer gc, int i, Maps map) {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.RUNLEFT;
			img_caseX -= 5;
			anim_LEFT.update(i);
			anim_ARM_L.update(i);
			AncienneDirection = direction;
		}
		else if(input.isKeyDown(Input.KEY_LSHIFT) && (AncienneDirection == Direction.RUNLEFT || AncienneDirection == Direction.LEFTKNIFE)) {
			direction = Direction.LEFTKNIFE;
			anim_KNIFE_L.update(i/2);
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.LEFTKNIFE || AncienneDirection == Direction.RUNLEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
				idle_ARM_LEFT.update((long) (i/2.5));
			}
		}
		System.out.println(ia.isFirstJump() + " : FJ_Cout");
		if(ia.isFirstJump()) {
			direction = Direction.RUNRIGHT;
			img_caseX += 5;
			anim_RIGHT.update(i);
			anim_ARM_R.update(i);
			AncienneDirection = direction;
		}
		else if(input.isKeyDown(Input.KEY_LSHIFT) && (AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE)) {
			direction = Direction.RIGHTKNIFE;
			anim_KNIFE_R.update(i/2);
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE) {
				direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));
				idle_ARM_RIGHT.update((long) (i/2.5));
			}
		}
		
		/*if(input.isKeyPressed(Input.KEY_SPACE)) {
			for (int j = 0; j < 10; j++) {
				if(AncienneDirection == Direction.RUNLEFT || AncienneDirection == Direction.LEFTKNIFE) {
					direction = Direction.JUMPLEFT;
					img_caseY -= 15;
					jump_LEFT.update(i);
				}
				else if(AncienneDirection == Direction.RUNRIGHT || AncienneDirection == Direction.RIGHTKNIFE) {
					direction = Direction.JUMPRIGHT;
					img_caseY -= 15;
					jump_RIGHT.update(i);
				}
			}
		}*/
	}
}
