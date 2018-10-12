package com.redes.game.tile;

import java.awt.Graphics;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.Id;

public class Door extends Tile{

	public Door(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.portal.getBufferedImage(),x , y, width, height, null);
	}

	@Override
	public void tick() {
		
	}

}
