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
	private String url;

	
	public Card(String name,String url){
		
		this.setName(name);
		this.etat = true;
		this.url = url;
		
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
}
