package Character;
import org.newdawn.slick.*;

enum Direction{
	idleRIGHT, idleLEFT, RIGHT, LEFT, JUMPRIGHT, JUMPLEFT
}

public class Character {
	private int img_caseX;
	private int img_caseY;
	private Image toast;
	private Animation idle_LEFT;
	private Animation idle_RIGHT;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	private Animation jump_RIGHT;
	private Animation jump_LEFT;
	private Direction direction;
	private Direction AncienneDirection;
	
	public Character() {
		super();
	}	
	
	public void renderCharacter(GameContainer gc, Graphics grphcs) {
		int posX = img_caseX;
		int posY = img_caseY;
		
		switch(direction) {
		case RIGHT: anim_RIGHT.draw(posX, posY);
			break;
		case LEFT: anim_LEFT.draw(posX, posY);
			break;
		case idleLEFT: idle_LEFT.draw(posX, posY);
			break;
		case idleRIGHT: idle_RIGHT.draw(posX, posY);
			break;
		case JUMPRIGHT: jump_RIGHT.draw(posX, posY);
			break;
		case JUMPLEFT: jump_LEFT.draw(posX, posY);
			break;
		}
	}
	public void initCharacter(GameContainer gc) throws SlickException {
		toast = new Image("./sprites/toast.png");
		
		img_caseX = 25;
		img_caseY = 300;
		idle_LEFT = getAnimation(1, -1, 0);
		idle_RIGHT = getAnimation(10, 8, 4);
		anim_RIGHT = getAnimation(10, 6 ,5);
		anim_LEFT = getAnimation(3, 0, 1);
		jump_RIGHT = getAnimation(10 , 3, 6);
		jump_LEFT = getAnimationJumpLeft(1, 7, 2);
		direction = Direction.LEFT;
		AncienneDirection = direction;
	}
	private Animation getAnimation(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x > max; x--) {
			anim.addFrame(toast.getSubImage(x*64, rowY*64, 64, 64), 50);
		}
		return anim;
	}
	private Animation getAnimationJumpLeft(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(toast.getSubImage(x*64, rowY*64, 64, 64), 50);
		}
		return anim;
	}
	public void updateCharacter(GameContainer gc, int i) {
		Input input = gc.getInput();
		//img_caseY += 7;
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.LEFT;
			img_caseX -= 2;
			anim_LEFT.update((long) (i/2.5));
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.LEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
			}
		}
		if(input.isKeyDown(Input.KEY_D)) {
			direction = Direction.RIGHT;
			img_caseX += 2;
			anim_RIGHT.update((long) (i/2.5));
			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.RIGHT) {
				direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));
			}
		}
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(AncienneDirection == Direction.LEFT) {
				direction = Direction.JUMPLEFT;
				img_caseY -= 5;
				jump_LEFT.update((long) (i/2.5));
				img_caseY += 5;
			}
			else if(AncienneDirection == Direction.RIGHT) {
				direction = Direction.JUMPRIGHT;
				img_caseY -= 5;
				jump_RIGHT.update((long) (i/2.5));
				img_caseY += 5;
			}
		}
	}
}
