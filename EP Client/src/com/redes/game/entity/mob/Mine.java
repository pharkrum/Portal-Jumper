package com.redes.game.entity.mob;

import java.awt.Graphics;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.Id;
import com.redes.game.entity.Entity;
import com.redes.game.tile.Tile;

public class Mine extends Entity{
	
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate = true;
	public int nada;
	
	public Mine(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
	
	}

	public void render(Graphics g) {
		g.drawImage(Game.fire[frame].getBufferedImage(),x , y, width, height, null);
	}

	public void tick(){
		
		for(Tile t:handler.tiles){
			if(!t.solid) break;
			if(t.getId()==Id.wall){
				
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling) falling = false;
				}
			}
		}
		if(animate){
			frameDelay++;
			if(frameDelay >= 3){
				frame++;
				if(frame>=8){
					frame = 0;
				}
				frameDelay=0;
			}
		}
	}

}
