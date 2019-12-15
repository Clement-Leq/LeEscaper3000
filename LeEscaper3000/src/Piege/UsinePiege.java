package Piege;

public class UsinePiege {
	public UsinePiege(){
		
	}
	public Pique getPique(int x) {
		Pique pique = null;
		
		switch (x) {
		case 0:  pique = new Pique(10);
				break;
		case 1: pique = new Pique(35);
				break;
		case 2: pique = new Pique(58);
				break;
		case 3: pique = new Pique(90);
				break;
		case 4: pique = new Pique(140);
				break;
		case 5: pique = new Pique(190);
				break;
		case 6: pique = new Pique(220);
				break;
		case 7: pique = new Pique(260);
				break;
		case 8: pique = new Pique(298);
				break;
		case 9: pique = new Pique(299);
				break;	
		}
		return pique;
		
	}
}
