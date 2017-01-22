package com.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.millesBornes.web.model.Card;
import com.millesBornes.web.model.Part;
import com.millesBornes.web.model.Player;



public final class Serveur {


	private final static Serveur serverInstance = new Serveur();
	private Part partie;
	private ServerSocket welcomeSocket;
	private Socket connectionSocket;
	private DataOutputStream outToClient;
	

	public Part partie() {
		return partie;
	}


	public void setPartie(Part partie) {
		this.partie = partie;
	}

	public static Serveur getInstance() {
		return serverInstance;
	}

	public Serveur() {
		
	}
		


}
