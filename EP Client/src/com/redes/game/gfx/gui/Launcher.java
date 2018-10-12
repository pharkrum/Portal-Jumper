package com.redes.game.gfx.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.socket.Network;

public class Launcher {
	public Handler handler;
	public Button[] buttons;
	public Network conex;
	public Thread thread2;
	
	public Launcher(Handler handler){
		this.handler = handler;
		buttons = new Button[3];
		
		buttons[0] = new Button(Game.getFrameWidth()/2 - 110,100,220,50,"Start Game",Color.WHITE);
		buttons[1] = new Button(Game.getFrameWidth()/2 - 110,170,220,50,"Exit Game",Color.WHITE);
		buttons[2] = new Button(Game.getFrameWidth()/2 - 130,240,260,50,"Connect to server",Color.WHITE);
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+10);
		
		for(int i = 0; i<buttons.length; i++){
			buttons[i].render(g);
		}
		
	}
	
	public void conect(){
		conex = new Network(12345,handler);
		thread2 = new Thread(conex);
		thread2.start();
		System.out.println("Client socket Thread started");
		buttons[2].setLabel("Connected");
		
	}
	
	public void disconect(){
		try {
			conex.closing = true;
			conex.client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client socket Thread off");
		buttons[2].setLabel("Connect to server");
	}

}
