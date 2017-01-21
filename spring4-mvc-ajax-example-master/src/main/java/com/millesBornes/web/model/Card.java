package com.millesBornes.web.model;


public class Card {

	//Attributes
	private String name;
	private boolean etat;

	
	public Card(String name){
		
		this.setName(name);
		this.etat = true;
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
}
