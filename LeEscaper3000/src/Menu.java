import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Character.Gardien;
import Character.Character;
import Maps.Maps;

public class Menu {
	
	private boolean enableStartMenu = true;
	private boolean enablePauseMenu = false;
	private boolean mouseOnPlay = false;
	private boolean mouseOnExit = false;
	private boolean mouseOnMenu = false;
	private boolean mouseOnBack = false;
	
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
	}

	public void menuStart (Image play, Image exit) {
		if(isMouseOnPlay()) {
			play.draw(101,102);
			
			if(Mouse.isButtonDown(0)){
				setEnableStartMenu(false);
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
