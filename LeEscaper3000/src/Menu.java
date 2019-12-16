import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Character.Character;
import Character.IA;

public class Menu {
	
	private boolean fini = false;
	private boolean enableStartMenu = true;
	private boolean enablePauseMenu = false;
	private boolean mouseOnPlay = false;
	private boolean mouseOnExit = false;
	private boolean mouseOnMenu = false;
	private boolean mouseOnBack = false;
	private boolean mouseOnRetry = false;
	private boolean resetGame = false;
	private int music_play = 1;
	private IA ia;
	private Character personnage;
	
	
	public boolean isMouseOnRetry() {
		return mouseOnRetry;
	}

	public void setMouseOnRetry(boolean mouseOnRetry) {
		this.mouseOnRetry = mouseOnRetry;
	}

	public boolean isFini() {
		return fini;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}

	public int getMusic_play() {
		return music_play;
	}

	public void setMusic_play(int music_play) {
		this.music_play = music_play;
	}

	public boolean isResetGame() {
		return resetGame;
	}

	public void setResetGame(boolean resetGame) {
		this.resetGame = resetGame;
	}

	public boolean isMouseOnPlay() {
		return mouseOnPlay;
	}

	public void setMouseOnPlay(boolean mouseOnPlay) {
		this.mouseOnPlay = mouseOnPlay;
	}

	public boolean isMouseOnExit() {
		return mouseOnExit;
	}

	public void setMouseOnExit(boolean mouseOnExit) {
		this.mouseOnExit = mouseOnExit;
	}

	public boolean isMouseOnMenu() {
		return mouseOnMenu;
	}

	public void setMouseOnMenu(boolean mouseOnMenu) {
		this.mouseOnMenu = mouseOnMenu;
	}

	public boolean isMouseOnBack() {
		return mouseOnBack;
	}

	public void setMouseOnBack(boolean mouseOnBack) {
		this.mouseOnBack = mouseOnBack;
	}

	public boolean isEnablePauseMenu() {
		return enablePauseMenu;
	}

	public void setEnablePauseMenu(boolean pauseMenu) {
		this.enablePauseMenu = pauseMenu;
	}
	
	public boolean isEnableStartMenu() {
		return enableStartMenu;
	}

	public void setEnableStartMenu(boolean enable) {
		this.enableStartMenu = enable;
	}

	public Menu() {
		// TODO Auto-generated constructor stub
		ia = IA.getInstance();
		personnage = Character.getInstance();
	}
	
	public void menuFini (GameContainer gc, Graphics grphcs, Image retry, Image retryBg, Jeu jeu) throws SlickException {
		retryBg.draw(540, 270, 280, 170);
		retry.draw(605, 335, 150, 70);
		if(isMouseOnRetry()) {
			retry.draw(602, 337, 150, 70);
			ia.setNbVie(3);
			
			if(Mouse.isButtonDown(0)){
				setFini(false);
				jeu.init(gc);
				jeu.renderJeu(gc, grphcs);
				setMusic_play(2);
			}
		}
		
	}

	public void gestionHp (Image hpBg, Image hp, Image hpFull, Image retry, Image retryBg) {
		//System.out.println(ia.getNbVie());
		if (ia.getNbVie() == 3) {
			hpBg.draw(personnage.getImg_caseX()- 500, 0, 150, 75);
			hp.draw(personnage.getImg_caseX()- 482, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 440, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 398, 28, 30, 30);
			hpFull.draw(personnage.getImg_caseX()- 490, 24, 45, 40);
			hpFull.draw(personnage.getImg_caseX()- 448, 24, 45, 40);
			hpFull.draw(personnage.getImg_caseX()- 406, 24, 45, 40);
		}
		else if (ia.getNbVie() == 2) {
			hpBg.draw(personnage.getImg_caseX()- 500, 0, 150, 75);
			hp.draw(personnage.getImg_caseX()- 482, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 440, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 398, 28, 30, 30);
			hpFull.draw(personnage.getImg_caseX()- 490, 24, 45, 40);
			hpFull.draw(personnage.getImg_caseX()- 448, 24, 45, 40);
		}
		else if (ia.getNbVie() == 1) {
			hpBg.draw(personnage.getImg_caseX()- 500, 0, 150, 75);
			hp.draw(personnage.getImg_caseX()- 482, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 440, 28, 30, 30);
			hp.draw(personnage.getImg_caseX()- 398, 28, 30, 30);;
			hpFull.draw(personnage.getImg_caseX()- 490, 24, 45, 40);
		}
		else if (ia.getNbVie() == 0) {
			this.setFini(true);
		}
	}
	
	public void menuStart (GameContainer gc, Image play, Image exit, Jeu jeu) throws SlickException {
		if(isMouseOnPlay()) {
			play.draw(101,102);
			ia.setNbVie(3);
			
			if(Mouse.isButtonDown(0)){
				setEnableStartMenu(false);
				jeu.init(gc);
				setMusic_play(2);
			}
		}
		else if (isMouseOnExit()) {
			exit.draw(98,202);
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
	}
	
	public void menuPause(GameContainer gc, Graphics grphcs, Image pause, Image menuBtn, Image backBtn, Jeu jeu) {
		if(isEnablePauseMenu()) {
			pause.draw(570, 200, 280, 380);
			menuBtn.draw(590, 290, 242, 70);
			backBtn.draw(642, 482, 140, 60);
			
			if(isMouseOnMenu()) {
				menuBtn.draw(588, 292, 242, 70);
				setMouseOnMenu(false);
				if(Mouse.isButtonDown(0)){
					setEnablePauseMenu(false);
					setEnableStartMenu(true);
					setMusic_play(1);
				}
			}
			if(isMouseOnBack()) {
				backBtn.draw(640, 484, 140, 60);
				setMouseOnBack(false);
				if(Mouse.isButtonDown(0)){
					jeu.renderJeu(gc, grphcs);
					setEnablePauseMenu(false);
				}
			}
		}
		else {
			jeu.renderJeu(gc, grphcs);
		}
	}
}
