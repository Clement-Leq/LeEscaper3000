import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import Character.*;
import Character.Character;
import Maps.Maps;


public class Jeu extends BasicGame{
	private Character personnage;
	private Compagnon compagnon;
	private Maps map;
	private Gardien mechantMur;
	private Pol_Cout policierCouteau;
	private Menu menu;
	private IA ia;
	
	//partie Menu
	private Image bg;
	private Image play;
	private Image playBg;
	private Image exitGame;
	private Image menuPause;
	private Image menuBtn;
	private Image back;
	private Image hp;
	private Image hpFull;
	private Image hpPanel;
	private Image retryBg;
	private Image retry;
	private Music musique_Fin;
	private Music musique_Base;
	
	public Jeu(String title) throws SlickException {
		super(title);
		ia = IA.getInstance();
		personnage = Character.getInstance();
		policierCouteau = Pol_Cout.getInstance();
		map = new Maps();
		mechantMur = new Gardien();
		//personnage = new Character();
		menu = new Menu();
	}

	public void render(GameContainer gc, Graphics grphcs) throws SlickException{
		renderMenu(gc, grphcs);
	}

	public void init(GameContainer gc) throws SlickException{
		map.iniMap();
		mechantMur.initGardien(gc);
		personnage.initCharacter(gc);
		policierCouteau.initGardCout(gc);
		initMenu(gc);
	}
	
	public void update(GameContainer gc, int i) throws SlickException{
		ia.updateIA();
		if (!menu.isEnableStartMenu() && !menu.isEnablePauseMenu() && !menu.isFini()) { 
			mechantMur.updateGardien(gc, i);
			personnage.updateCharacter(gc, i, map);
			//policierCouteau.updateGardCout(gc, i, map);
			
			if(map.isGrounded(policierCouteau.getImg_caseX(), policierCouteau.getImg_caseY()+81, "Sol")) {
				policierCouteau.setImg_caseY(policierCouteau.getImg_caseY()+4);
			}
			if(ia.isStartPolice()) {
				ia.deplacementGardien(personnage.getImg_caseX(), personnage.getImg_caseY(), gc, i, map);
			}
		}
		updateMenu(gc, i);
	}
	
	// Partie Menu
	
	public void renderMenu(GameContainer gc, Graphics grphcs) throws SlickException{
		if (menu.isEnableStartMenu()) {
			bg.draw(0, 0, 1440, 768);
			grphcs.drawString("LE ESCAPER 3000 !!", 100, 50);
			play.draw(100, 100);
			exitGame.draw(100, 200);
			menu.menuStart(gc, playBg, exitGame, this);
		}
		else if (menu.isFini()) {
			menu.menuFini(gc, grphcs, retry, retryBg, this);
		}
		else {
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
			ia.setStartPolice(false);
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
		else if((posX > 605 && posX < 755) && (posY > 363 && posY < 433)) {
			menu.setMouseOnRetry(true);
		}
		else {
			menu.setMouseOnPlay(false);
			menu.setMouseOnExit(false);
			menu.setMouseOnMenu(false);
			menu.setMouseOnBack(false);
			menu.setMouseOnRetry(false);
		}
		if(menu.getMusic_play() == 1) {
			musique_Fin.loop();
			musique_Fin.setVolume(0.30f);
			menu.setMusic_play(0);
		}
		else if (menu.getMusic_play() == 2) {
			musique_Base.loop();
			musique_Base.setVolume(0.50f);
			menu.setMusic_play(0);
		}
	}
	
	public void initMenu (GameContainer gc) throws SlickException {
		bg = new Image("./images/Background.png");
		play = new Image("./images/BTN_PLAY.png");
		playBg = new Image("./images/BTN_PLAY_BG.png");
		exitGame = new Image("./images/BTN_Exit.png");
		menuPause = new Image("./images/PAUSE_PRESET.png");
		menuBtn = new Image("./images/BTN_MENU.png");
		back = new Image("./images/BTN_BACK.png");
		musique_Fin = new Music("./audio/Credit_Theme.wav");
		musique_Base = new Music("./audio/Base_Theme.wav");
		hp = new Image("./images/HP.png");
		hpFull = new Image("./images/HP_FULL.png");
		hpPanel = new Image("./images/HP_PANEL.png");
		retry = new Image("./images/BTN_Retry.png");
		retryBg = new Image("./images/BG_MORT.png");
		map.iniMap();
	}
	
	public void renderJeu(GameContainer gc, Graphics grphcs) {
		grphcs.translate(-personnage.getImg_caseX()+600, 0);
		map.renderMap();
		mechantMur.renderGardien(gc, grphcs);
		policierCouteau.renderGardCout(gc, grphcs);
		personnage.renderCharacter(gc, grphcs);
		//System.out.println(personnage.getImg_caseX());
		ia.dessinerRectangles(grphcs);
		menu.gestionHp(hpPanel, hp, hpFull, retry, retryBg);
	}
}