package Character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;

import Maps.Maps;

public class IA {

	private Pol_Cout policierCouteau = Pol_Cout.getInstance();
	private boolean firstJump = false;
	private boolean jump = false;
	private float posJump = 0;
	
	private static class Singleton{
		private static IA INSTANCE = new IA();
	}
	public static IA getInstance() {
		return Singleton.INSTANCE;
	}
	
	public float getPosJump() {
		return posJump;
	}

	public void setPosJump(float posJump) {
		this.posJump = posJump;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isFirstJump() {
		return firstJump;
	}

	public void setFirstJump(boolean firstJump) {
		this.firstJump = firstJump;
	}

	public IA() {
		// TODO Auto-generated constructor stub
	}
	
	public void deplacementGardien(float posX_Player, float posY_Player, GameContainer gc, int i, Maps map) {
		//System.out.println(policierCouteau.getImg_caseX());
		//System.out.println(policierCouteau.getImg_caseY());
		
		if (this.isJump()) {
			if (policierCouteau.getImg_caseX() <= this.getPosJump()) {
				policierCouteau.depDroite(gc, i, map);
			}
			else{
				policierCouteau.jump(gc, i, map);
				this.setJump(false);
			}
		}
		else if ((policierCouteau.getImg_caseY() <= posY_Player-16) && (policierCouteau.getImg_caseX() < posX_Player)) {
			policierCouteau.depDroite(gc, i, map);
		}
		else if ((policierCouteau.getImg_caseX() > posX_Player) && (policierCouteau.getImg_caseY() <= posY_Player-16)) {
			policierCouteau.depGauche(gc, i, map);
		}
		else {
			policierCouteau.depDroite(gc, i, map);
		}
		
		
	}
	
	public void jump(GameContainer gc, int i, Maps map) {
		policierCouteau.jump(gc, i, map);
	}
}