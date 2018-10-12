package com.redes.game.entity.mob;

import java.awt.Graphics;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.Id;
import com.redes.game.entity.Entity;
import com.redes.game.tile.Tile;

public class Player extends Entity{
	
	private int frame = 0;
	private int frameDelay = 0;
	
	private boolean animate = false;
	
	public Player(int x, int y, int width, int height, Id id, Handler handler) {
		super(x, y, width, height, id, handler);
	}

	public void render(Graphics g) {
		if(id == Id.player1){
			if(facing == 0){
				g.drawImage(Game.player1[frame+8].getBufferedImage(),x , y, width, height, null);
			}else if(facing == 1){
				g.drawImage(Game.player1[frame].getBufferedImage(),x , y, width, height, null);	
			}
		}else{
			if(facing == 0){
				g.drawImage(Game.player2[frame+8].getBufferedImage(),x , y, width, height, null);
			}else if(facing == 1){
				g.drawImage(Game.player2[frame].getBufferedImage(),x , y, width, height, null);	
			}
		}
	}
	
	public void die(){
		Game.showDeathScreen = true;
		Game.handler.removeEntity(this);
	}

	public void tick() {
		x += velX;
		y += velY;
		if (velX != 0) animate = true;
		else animate = false;
		for(Tile t:handler.tiles){
			if(!t.solid) break;
			if(t.getId()==Id.wall){
				if(getBoundsTop().intersects(t.getBounds())){
					setVelY(0);	
					if(jumping){
						jumping = false;
						gravity = 0.8;
						falling = true;
					} 
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling) falling = false;
				
				}else{
					if(!falling && !jumping){
						gravity =  0.8;
						falling = true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(0);
					x = t.getX()+t.width+5;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(0);
					x = t.getX()-t.width-5;
				}
			}
		}
		
		for(int i=0;i<handler.entitys.size();i++){
			Entity e = handler.entitys.get(i);
			if(e.getId() == Id.mine){
				if(getBoundsBottom().intersects(e.getBoundsTop())){
					e.die();
				} else if(getBounds().intersects(e.getBounds())){
					die();
				}
			}
		}
		
		for(Tile t: handler.tiles){
			if(t.id == Id.door){
				if(getBounds().intersects(t.getBounds()) && id == Id.player2){
					Game.LocalPoints++;
					die();
				}
				else if(getBounds().intersects(t.getBounds()) && id == Id.player1){
					die();					
				}
			}
		}
		
		if(jumping){
			gravity-= 0.17;
			setVelY((int)-gravity);
			if(gravity <= 0.0){
				jumping = false;
				falling = true;
			}
			
		}
		if(falling){
			gravity+=0.19;
			setVelY((int)gravity);
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
