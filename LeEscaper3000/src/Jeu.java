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
	private Menu menu;
	
	//partie Menu
	private Image bg;
	private Image play;
	private Image playBg;
	private Image exitGame;
	private Image exitGameBg;
	private Image menuPause;
	private Image menuBtn;
	private Image back;
	private boolean selectmenu = false;
	private boolean selectmenu2 = false;
	private boolean selectpause = false;
	private boolean selectpause2 = false;
	private boolean enable = true;
	private boolean pause = false;
	
	public Jeu(String title) {
		super(title);
		map = new Maps();
		mechantMur = new Gardien();
		personnage = new Character();
		menu = new Menu();
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
		if (!menu.isEnableStartMenu() && !menu.isEnablePauseMenu()) { 
			mechantMur.updateGardien(gc, i);
			personnage.updateCharacter(gc, i, map);
			if(map.isGrounded(personnage.getImg_caseX(), personnage.getImg_caseY()+64, "Sol")) {
				personnage.setImg_caseY(personnage.getImg_caseY()+4);
			}
		}
		updateMenu(gc, i);
	}
	
	// Partie Menu
	
	public void renderMenu(GameContainer gc, Graphics grphcs){
		if (menu.isEnableStartMenu()) {
			bg.draw(0, 0, 1440, 768);
			grphcs.drawString("LE ESCAPER 3000 !!", 100, 50);
			play.draw(100, 100);
			exitGame.draw(100, 200);
			menu.menuStart(playBg, exitGame);
		}
		else {
			renderJeu(gc, grphcs);
			menu.menuPause(gc, grphcs, menuPause, menuBtn, back, this);
		}
	}
	
	public void updateMenu(GameContainer gc, int i) {
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		//System.out.println("posX :" + posX);
		//System.out.println("posY :" + posY);
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			menu.setEnablePauseMenu(true);
		}
		if((posX > 100 && posX < 300) && (posY > 624 && posY < 669)) {
			menu.setMouseOnPlay(true);
		}
		else if((posX > 99 && posX < 128) && (posY > 541 && posY < 568)) {
			menu.setMouseOnExit(true);
		}
		else if((posX > 590 && posX < 832) && (posY > 406 && posY < 479)) {
			menu.setMouseOnMenu(true);
		}
		else if((posX > 641 && posX < 782) && (posY > 226 && posY < 286)) {
			menu.setMouseOnBack(true);
		}
		else {
			menu.setMouseOnPlay(false);
			menu.setMouseOnExit(false);
			menu.setMouseOnMenu(false);
			menu.setMouseOnBack(false);
		}
		
	}
	
	public void initMenu (GameContainer gc) throws SlickException {
		bg = new Image("./images/Background.png");
		play = new Image("./images/BTN_PLAY.png");
		playBg = new Image("./images/BTN_PLAY_BG.png");
		exitGame = new Image("./images/BTN_Exit.png");
		exitGameBg = new Image("./images/BTN_EXIT2.png");
		menuPause = new Image("./images/PAUSE_PRESET.png");
		menuBtn = new Image("./images/BTN_MENU.png");
		back = new Image("./images/BTN_BACK.png");
		map.iniMap();
	}
	
	public void renderJeu(GameContainer gc, Graphics grphcs) {
		map.renderMap();
		mechantMur.renderGardien(gc, grphcs);
		personnage.renderCharacter(gc, grphcs);
	}
}