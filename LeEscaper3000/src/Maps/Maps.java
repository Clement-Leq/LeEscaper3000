package Maps;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Maps extends BasicGame{
	
	private TiledMap map;
	private int tilesSize;
	
	public Maps(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		map.render(0,0);
		
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		map = new TiledMap("./maps/Java_Projet.tmx");
		tilesSize = 32;
	}
	
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}