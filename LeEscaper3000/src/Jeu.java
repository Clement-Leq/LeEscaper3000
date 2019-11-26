import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import Character.Character;
import Character.Gardien;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private Maps map;
	private Gardien mechantMur;
	
	//partie Menu
	private Image play;
	private Image playBg;
	private Image exitGame;
	private Image exitGameBg;
	private Image bg;
	private boolean select = false;
	private boolean select2 = false;
	private boolean enable = true;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		mechantMur = new Gardien();
		personnage = new Character();
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException{
		renderMenu(gc, grphcs);
		
	}

	public void init(GameContainer gc) throws SlickException{
		map.iniMap();
		mechantMur.initGardien(gc);
		personnage.initCharacter(gc);
		initMenu(gc);
	}
	
	public void update(GameContainer gc, int i) throws SlickException{
		mechantMur.updateGardien(gc, i);
		personnage.updateCharacter(gc, i, map);
		if(map.isGrounded(personnage.getImg_caseX(), personnage.getImg_caseY()+64, "Sol")) {
			personnage.setImg_caseY(personnage.getImg_caseY()+4);
		}
		updateMenu(gc, i);		
	}
	
	// Partie Menu
	
	public void renderMenu(GameContainer gc, Graphics grphcs){
		if (enable) {
			bg.draw(0, 0, 1440, 768);
			grphcs.drawString("LE ESCAPER 3000 !!", 100, 50);
			play.draw(100, 100);
			exitGame.draw(100, 200);
			if(select) {
				playBg.draw(101,102);
				select = false;
				if(Mouse.isButtonDown(0)){
					enable = false;
				}
			}
			if(select2) {
				exitGameBg.draw(96,202);
				select2 = false;
				if(Mouse.isButtonDown(0)){
					enable = false;
					System.exit(0);
				}
			}
		}
		if(!enable) {
			map.renderMap();
			mechantMur.renderGardien(gc, grphcs);
			personnage.renderCharacter(gc, grphcs);
		}
	}
	
	public void updateMenu(GameContainer gc, int i) {
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		//System.out.println("posX :" + posX);
		//System.out.println("posY :" + posY);
		if((posX > 100 && posX < 300) && (posY > 624 && posY < 669)) {
			select = true;
		}
		if((posX > 99 && posX < 128) && (posY > 541 && posY < 568)) {
			select2 = true;
		}
		if(input.isKeyDown(Input.KEY_SPACE)) {
			
		}
	}
	
	public void initMenu (GameContainer gc) throws SlickException {
		bg = new Image("./images/Background.png");
		play = new Image("./images/BTN_PLAY.png");
		playBg = new Image("./images/BTN_PLAY_BG.png");
		exitGame = new Image("./images/BTN_Exit.png");
		exitGameBg = new Image("./images/BTN_EXIT2.png");
		map.iniMap();
	}
}