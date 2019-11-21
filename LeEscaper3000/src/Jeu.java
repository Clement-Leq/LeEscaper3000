import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

enum Direction{
	idleLEFT, RIGHT, LEFT
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
		default: idle_LEFT.draw(posX, posY);
			break;
		}
	}

	public void init(GameContainer gc) throws SlickException {
		toast = new Image("./sprites/toast.png");
		img_caseX = 25;
		img_caseY = 300;
		idle_LEFT = getAnimationidle_LEFT();
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
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		
		
		if(input.isKeyDown(Input.KEY_A)) {
			direction = Direction.LEFT;
			img_caseX -= 2;
			anim_LEFT.update((long) (i/2.5));
		}
		else {
			direction = Direction.idleLEFT;
			idle_LEFT.update((long) (i/2.5));
		}
		if(input.isKeyDown(Input.KEY_D)) {
			direction = Direction.RIGHT;
			img_caseX += 2;
			anim_RIGHT.update((long) (i/2.5));
		}
		else {
			direction = Direction.RIGHT;
			idle_RIGHT.update((long) (i/2.5));
		}
	}

}
