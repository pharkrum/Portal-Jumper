package com.redes.game.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.redes.game.Game;
import com.redes.game.Handler;
import com.redes.game.Id;
import com.redes.game.entity.mob.Player;

public class Network extends Thread{
	public ServerSocket server;
	public int port;
	
	public Handler handler;
	public Player player1, player2;
	
	public DataInputStream input;
	public DataOutputStream output;
	
	public String msg;
	public boolean clientStartPlaying = false;
	
	public Socket client;
	public boolean closing = false;

	public Network(int port, Handler handler){
		this.port = port;
		this.handler = handler;
	}
	// SERVER
	public void run(){
		
		try {
			server = new ServerSocket(port); //open server
			System.out.println("Server listening on the port: "+port);
			
			try{
				client = server.accept(); //waiting client
			}catch(SocketException exp){ // Caused by abrupt disconnection with accept method
				System.out.println("Connection down");
			}
			
			if(client != null){
				System.out.println("Client connected: "+ client.getInetAddress().getHostAddress()); //client connected
				
				Game.firstConect = true;

				input = new DataInputStream(client.getInputStream());
				output = new DataOutputStream(client.getOutputStream()); 
				
				while(!closing){
					
					if(!clientStartPlaying){
						output.writeUTF("0#0#0#false#0.0#"+Game.LocalPoints);//Null player to reset
						try{
							input.readUTF();							
						}catch(EOFException eof){ // Caused by abrupt disconnection of the client while there is a connection
							System.out.println("Client disconnected....Reestablishing server");
							Game.firstConect = false;
							Game.launcher.disconect();
							Game.launcher.conect();	
						}
					}
					
					if(Game.playing && !Game.showDeathScreen){
						
						for(int i=0;i<handler.entitys.size();i++){
							if(handler.entitys.get(i).id == Id.player1)
								player1 = (Player)handler.getEntity(i);
							else if(handler.entitys.get(i).id == Id.player2)
								player2 = (Player)handler.getEntity(i);		
						}
						
						String player1Info;
						
						msg = input.readUTF();
						clientStartPlaying = true;
						
						String[] parts = msg.split("#");
						int x = Integer.parseInt(parts[0]);
						int y = Integer.parseInt(parts[1]);
						int velX = Integer.parseInt(parts[2]);
						boolean jumping = Boolean.parseBoolean(parts[3]);
						double gravity = Double.parseDouble(parts[4]);
						Game.EnemyPoints = Integer.parseInt(parts[5]);

						if(velX < -1){
							player2.facing = 0;
						}else if(velX > 1){
							player2.facing = 1;
						}
						
						player2.setX(x);
						player2.setY(y);
						player2.setVelX(velX);
						if (gravity == 10.0){
							player2.gravity = gravity;
							player2.jumping = jumping;
						}
						
						if(Game.EnemyPoints == 3){
							
							clientStartPlaying = false;
							output.writeUTF("0#0#0#false#0.0#"+Game.LocalPoints);//Null player to reset
							
							Game.showDeathScreen = true;
							Game.enemyWin = true;
							Game.gameOver = true;
						}
						
						else if (Game.LocalPoints == 3){
							player1Info = player1.getX()+"#"+player1.getY()+"#"+player1.getVelX()+ "#" + player1.jumping + "#" + player1.gravity+"#"+Game.LocalPoints;
							output.writeUTF(player1Info);
							
							output.writeUTF("0#0#0#false#0.0#"+Game.LocalPoints);//Null player to reset
							clientStartPlaying = false;
							
							Game.showDeathScreen = true;
							Game.localWin = true;
							Game.gameOver = true;
						}
						else{
							player1Info = player1.getX()+"#"+player1.getY()+"#"+player1.getVelX()+ "#" + player1.jumping + "#" + player1.gravity+"#"+Game.LocalPoints;
							output.writeUTF(player1Info);
						}
					}
				}	
			}	
		}catch(SocketException ex){ //Caused when we get a port bind error or client disconnect when we playing
	        System.out.println("Connection Down");
	        
	        Game.playing = false;
	        Game.firstConect = false;
			Game.launcher.disconect();
			Game.launcher.conect();
			
	        Game.launcher.buttons[2].setLabel("Server online");
	    }catch (IOException e) { // Caused when we get a error on input and outputStreams
			e.printStackTrace();
		}
	}	
}
