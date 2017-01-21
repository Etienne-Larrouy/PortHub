package com.server;

import com.millesBornes.web.model.Player;

public final class Server {


	private final static Server serverInstance = new Server();
	private Player p1 = new Player("Joueur1");

	public static Server getInstance() {
		return serverInstance;
	}

	private Server() {
	
	}
	
	public Player getPlayer(){
		return p1;
	}
	
	public Player setPlayer(){
		return p1;
	}

}
