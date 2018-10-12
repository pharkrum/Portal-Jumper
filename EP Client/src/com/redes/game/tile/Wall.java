package com.redes.game.tile;

import java.awt.Graphics;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.Id;

public class Wall extends Tile{

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		g.drawImage(Game.grass.getBufferedImage(),x , y, width, height, null);
	}

	public void tick() {
		
	}

}
