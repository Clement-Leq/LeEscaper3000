package Character;
import org.newdawn.slick.*;

enum Direction2{
	idleRIGHT, idleLEFT, RIGHT, LEFT, JUMPRIGHT, JUMPLEFT
}

public class Gardien {
	private int img_caseX;
	private int img_caseY;
	private Image gardien;
	private Animation anim_muscles;
	private Animation idle_LEFT;
	private Animation idle_RIGHT;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	private Animation jump_RIGHT;
	private Animation jump_LEFT;
	private Direction direction;
	private Direction AncienneDirection;
	
	public Gardien() {
		super();
	}	
	
	public void renderGardien(GameContainer gc, Graphics grphcs) {
		int posX = img_caseX;
		int posY = img_caseY;
		anim_muscles.draw(9 * 32, 11 * 32);
	}
	public void initGardien(GameContainer gc){
		try {
			gardien = new Image("./sprites/Police_mur_gros(1).png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		img_caseX = 150;
		img_caseY = 300;
		anim_muscles = getAnimation(0, 27, 0);
		direction = Direction.RUNLEFT;
		AncienneDirection = direction;
	}
	private Animation getAnimation(int dep, int max, int rowY) {
		Animation anim = new Animation(false);
		for(int x = dep; x < max; x++) {
			anim.addFrame(gardien.getSubImage(x*66, rowY*66, 70, 95), 150);
		}
		for(int x = max; x > dep; x--) {
			anim.addFrame(gardien.getSubImage(x*66, rowY*66, 70, 95), 150);
		}
		return anim;
	}
	public void updateGardien(GameContainer gc, int i) {
		Input input = gc.getInput();
		anim_muscles.update(i);
		
	}
}