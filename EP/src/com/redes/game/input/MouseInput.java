package com.redes.game.input;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.redes.game.Game;
import com.redes.game.gfx.gui.Button;

public class MouseInput implements MouseListener, MouseMotionListener{

	public int x,y;

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		try{
			for(int i=0;i<Game.launcher.buttons.length; i++){
				Button button = Game.launcher.buttons[i];
				
				if(x>= button.getX() && y>= button.getY() && x<= button.getX()+button.getWidth() && y <= button.getY() + button.getHeight())
					button.setColor(Color.BLACK);
				else
					button.setColor(Color.white);			
			}
		}catch(java.lang.NullPointerException exp){
			//System.out.println("starting buttons");
		}
		
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if(!Game.playing){
			for(int i=0;i<Game.launcher.buttons.length; i++){
				Button button = Game.launcher.buttons[i];
				
				if(x>= button.getX() && y>= button.getY() && x<= button.getX()+button.getWidth() && y <= button.getY() + button.getHeight())
					button.triggerEvent();
			}
		}
		
	}

	public void mouseReleased(MouseEvent e) {

	}

}
