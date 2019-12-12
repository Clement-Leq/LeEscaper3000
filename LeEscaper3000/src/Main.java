import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		int largeurAffichage = 3000;
		int hauteurAffichage = 768;
		boolean siPleinEcran = false;
		try {
			AppGameContainer app = new AppGameContainer( new Jeu("LeEscaper3000")  );
			app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
			app.setTargetFrameRate(60);
			app.start();
		}
		catch(SlickException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);			
		}
	
	}

}
