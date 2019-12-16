package Character;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Maps.Maps;

public class IA {

	private Pol_Cout policierCouteau;
	private Character personnage;;
	private boolean jump = false;
	private boolean startPolice = false;
	private int debutTemps = 0;
	private final int TEMPSTARTPOLICE = 25;
	private boolean couteauActif = false;
	private int debut = 0;
	private final int DUREEANIM = 25;
	private int nbVie = 3;
	
	private static class Singleton{
		private static IA INSTANCE = new IA();
	}
	public static IA getInstance() {
		return Singleton.INSTANCE;
	}
	
	public int getNbVie() {
		return nbVie;
	}
	
	public void setNbVie(int nbVie) {
		this.nbVie = nbVie;
	}

	public boolean isCouteauActif() {
		return couteauActif;
	}

	public boolean isStartPolice() {
		return startPolice;
	}

	public void setStartPolice(boolean startPolice) {
		this.startPolice = startPolice;
	}

	public void setCouteauActif(boolean couteauActif) {
		this.couteauActif = couteauActif;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public IA() {
		// TODO Auto-generated constructor stub
		policierCouteau = Pol_Cout.getInstance();
		personnage = Character.getInstance();
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
        Rectangle rectanglePolice = getRectanglePolice();
        Rectangle rectanglePerso = getRectanglePerso();
        grphcs.draw(rectanglePolice);
        grphcs.draw(rectanglePerso);
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
				if(this.siAttaqueTouche()) {
					this.setNbVie(this.getNbVie() - 1);
				}
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
				policierCouteau.setImg_caseX(policierCouteau.getImg_caseX()+8);
			}
			else if ((policierCouteau.getImg_caseY() <= posY_Player-16) && (policierCouteau.getImg_caseX() < posX_Player)) {
				policierCouteau.depDroite(gc, i);
			}
			else if ((policierCouteau.getImg_caseX() > posX_Player) && (policierCouteau.getImg_caseY() <= posY_Player-16)) {
				policierCouteau.depGauche(gc, i);
			}
		}
	}
	
	public void updateIA () {
		debutTemps += 1;
		if(debutTemps > TEMPSTARTPOLICE) {
			this.setStartPolice(true);
			debutTemps = 0;
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