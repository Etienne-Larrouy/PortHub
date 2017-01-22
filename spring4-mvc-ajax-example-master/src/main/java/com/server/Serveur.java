package com.server;

import com.millesBornes.web.model.Part;

public final class Serveur {


	private final static Serveur serverInstance = new Serveur();
	private Part partie;
	

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
