package Character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import Maps.Maps;

public class IA {

	private Pol_Cout policierCouteau = Pol_Cout.getInstance();
	private Character personnage = Character.getInstance();
	private boolean firstJump = false;
	private boolean estSurSol = false;
	private boolean jump = false;
	private boolean couteauActif = false;
	private float posJumpX = 0;
	private float posJumpY = 0;
	private int debut = 0;
	private final int DUREEANIM = 25;
	
	private static class Singleton{
		private static IA INSTANCE = new IA();
	}
	public static IA getInstance() {
		return Singleton.INSTANCE;
	}
	
	public boolean isCouteauActif() {
		return couteauActif;
	}

	public void setCouteauActif(boolean couteauActif) {
		this.couteauActif = couteauActif;
	}

	public boolean isEstSurSol() {
		return estSurSol;
	}

	public void setEstSurSol(boolean estSurSol) {
		this.estSurSol = estSurSol;
	}

	public float getPosJumpX() {
		return posJumpX;
	}
	
	public float getPosJumpY() {
		return posJumpY;
	}

	public void setPosJump(float posJumpX, float posJumpY) {
		this.posJumpX = posJumpX;
		this.posJumpY = posJumpY;
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
	
	private Rectangle getRectanglePolice() {
        float PolicePosX = policierCouteau.getImg_caseX();
        float PolicePosY = policierCouteau.getImg_caseY()+15;
        float PoliceTaille = 70;
        return new Rectangle(PolicePosX, PolicePosY, PoliceTaille, PoliceTaille);
    }
	
	private Rectangle getRectanglePerso() {
        float PersoPosX = personnage.getImg_caseX();
        float PersoPosY = personnage.getImg_caseY()+20;
        float PersoTailleX = 32;
        float PersoTailleY = 44;
        return new Rectangle(PersoPosX, PersoPosY, PersoTailleX, PersoTailleY);
    }
	
	public void dessinerRectangles(Graphics grphcs) {
        grphcs.setColor(Color.white);
        Rectangle rectangleLoki = getRectanglePolice();
        Rectangle rectangleProjectile = getRectanglePerso();
        grphcs.draw(rectangleLoki);
        grphcs.draw(rectangleProjectile);
    }
	
	public boolean siAttaqueTouche() {
        boolean siTouche = false;

        Rectangle rectanglePolice = getRectanglePolice();
        Rectangle rectanglePerso = getRectanglePerso();

        if (rectanglePolice.intersects(rectanglePerso)) {
            siTouche = true;
        }

        return siTouche;
    }
	
	public void deplacementGardien(float posX_Player, float posY_Player, GameContainer gc, int i, Maps map) {
		//System.out.println(policierCouteau.getImg_caseX());
		//System.out.println(policierCouteau.getImg_caseY());
		
		if(isCouteauActif()) {
			policierCouteau.couteau(gc, i);
			debut += 1;
			if(debut > DUREEANIM) {
				this.setCouteauActif(false);
				debut = 0;
			}			
		}
		else {
			this.coupCouteau(posX_Player, posY_Player);
			/*if (this.isJump()) {
				if (policierCouteau.getImg_caseX() <= this.getPosJumpX()) {
					policierCouteau.depDroite(gc, i);
				}
				else if (policierCouteau.getImg_caseY() > posY_Player || policierCouteau.getImg_caseY() < posY_Player){
					
					while (policierCouteau.getImg_caseY() > posY_Player) {
						policierCouteau.setImg_caseX(policierCouteau.getImg_caseX()+2);
						policierCouteau.jump(gc, i, map);
					}		
					this.setJump(false);
				}
			}*/
			if (policierCouteau.getImg_caseY() > posY_Player) {
				policierCouteau.jump(gc, i, map);
				policierCouteau.setImg_caseX(policierCouteau.getImg_caseX()+10);
			}
			else if ((policierCouteau.getImg_caseY() <= posY_Player-16) && (policierCouteau.getImg_caseX() < posX_Player)) {
				policierCouteau.depDroite(gc, i);
			}
			else if ((policierCouteau.getImg_caseX() > posX_Player) && (policierCouteau.getImg_caseY() <= posY_Player-16)) {
				policierCouteau.depGauche(gc, i);
			}
		}
	}
	
	public void jump(GameContainer gc, int i, Maps map) {
		policierCouteau.jump(gc, i, map);
	}
	
	public void coupCouteau(float posX_Player, float posY_Player) {
		if ((policierCouteau.getImg_caseX() >= posX_Player-15) && (policierCouteau.getImg_caseY() <= posY_Player-16)) {
			this.setCouteauActif(true);
		}
	}
	
}