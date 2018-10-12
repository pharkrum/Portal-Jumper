package com.redes.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.redes.game.Game;
import com.redes.game.Id;
import com.redes.game.entity.Entity;

public class KeyInput implements KeyListener {

	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		for(Entity en:Game.handler.entitys){
			if(en.getId()==Id.player1){
				switch(key){
				case KeyEvent.VK_W:
					if(!en.jumping && en.gravity == 0.99){
						en.jumping = true;
						en.gravity = 10.0;
					}
					break;
				case KeyEvent.VK_A:
					en.setVelX(-5);
					en.facing = 0;
					break;
				case KeyEvent.VK_D:
					en.setVelX(5);
					en.facing = 1;
					break;
				}  
			}
		}	
	}

	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		for(Entity en:Game.handler.entitys){
			if(en.getId()==Id.player1){
				switch(key){
				case KeyEvent.VK_W:
					en.setVelY(0);
					break;
				case KeyEvent.VK_A:
					en.setVelX(0);
					break;
				case KeyEvent.VK_D:
					en.setVelX(0);
					break;
				} 
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

}
