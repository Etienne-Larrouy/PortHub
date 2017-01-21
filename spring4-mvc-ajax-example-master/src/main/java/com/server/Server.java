package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.millesBornes.web.model.Card;
import com.millesBornes.web.model.Part;
import com.millesBornes.web.model.Player;

public final class Server {


	private final static Server serverInstance = new Server();
	private static Part partie;
	

	public static Part getPartie() {
		return partie;
	}


	public static void setPartie(Part partie) {
		Server.partie = partie;
	}


	public static void main(String[] args) throws IOException {
	    partie = new Part("localhost",1);
		partie.add_player(new Player("p1"));
		
		for(int i = 0;i<7;i++){
			Card card = partie.give_card();
			partie.getList_Player().get(0).add_card(card);
		
			Server s = new Server();
			ServerSocket welcomeSocket = new ServerSocket(6789);
			
			while(true)
			{
				Socket connectionSocket = welcomeSocket.accept();
			
			}
			
	}
		
		
	}


	public static Server getInstance() {
		return serverInstance;
	}

	private Server() {
	
	}

}
