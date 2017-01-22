package com.millesBornes.web.model;

import java.io.Serializable;

public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
