import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

enum Direction{
	idleRIGHT, idleLEFT, RIGHT, LEFT
}

public class Jeu extends BasicGame{
	private int img_caseX;
	private int img_caseY;
	private Image toast;
	private Animation idle_LEFT;
	private Animation idle_RIGHT;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	private Direction direction;
	private Direction AncienneDirection;
	
	public Jeu(String title) {
		super(title);
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException {
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
		}
	}

	public void init(GameContainer gc) throws SlickException {
		toast = new Image("./sprites/toast.png");
		img_caseX = 25;
		img_caseY = 300;
		idle_LEFT = getAnimationidle_LEFT();
		idle_RIGHT = getAnimationIdle_Right();
		anim_RIGHT = getAnimationRight();
		anim_LEFT = getAnimationLeft();
		direction = Direction.idleLEFT;
	}
	
	private Animation getAnimationidle_LEFT() {
		Animation anim = new Animation(false);
		for(int x = 0; x < 2; x++) {
			anim.addFrame(toast.getSubImage(x*64, 0*64, 64, 64), 50);
		}
		return anim;
	}
	private Animation getAnimationRight() {
		Animation anim = new Animation(false);
		for(int x = 7; x < 11; x++) {
			anim.addFrame(toast.getSubImage(x*64, 5*64, 64, 64), 50);
		}
		return anim;
	}
	private Animation getAnimationLeft() {
		Animation anim = new Animation(false);
		for(int x = 0; x < 4; x++) {
			anim.addFrame(toast.getSubImage(x*64, 1*64, 64, 64), 50);
		}
		return anim;
	}
	private Animation getAnimationIdle_Right() {
		Animation anim = new Animation(false);
		for(int x = 9; x < 11; x++) {
			anim.addFrame(toast.getSubImage(x*64, 4*64, 64, 64), 50);
		}
		return anim;
	}
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.LEFT;
			img_caseX -= 2;
			anim_LEFT.update((long) (i/2.5));
				/*direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));*/

			AncienneDirection = direction;
		}
		else if(input.isKeyDown(Input.KEY_D)) {
			direction = Direction.RIGHT;
			img_caseX += 2;
			anim_RIGHT.update((long) (i/2.5));
				/*direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));*/

			AncienneDirection = direction;
		}
		else {
			if(AncienneDirection == Direction.LEFT) {
				direction = Direction.idleLEFT;
				idle_LEFT.update((long) (i/2.5));
			}
			if(AncienneDirection == Direction.RIGHT) {
				direction = Direction.idleRIGHT;
				idle_RIGHT.update((long) (i/2.5));
			}
		}
				
	}

}
