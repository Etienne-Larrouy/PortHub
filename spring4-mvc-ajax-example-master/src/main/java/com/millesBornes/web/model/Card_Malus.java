package com.millesBornes.web.model;


public class Card_Malus extends Card{
	
	private String malus;

	public Card_Malus(String name,String malus) {
		super(name);
		// TODO Auto-generated constructor stub
		this.malus = malus;
	}
	
	
	public void Action(){
		if(malus.equals("accidente")){
			System.out.println("accident");
		}
		if(malus.equals("snow") || malus.equals("traffic") || malus.equals("manif")){
			System.out.println("snow traffic or manif");
		}
		if(malus.equals("fast")){
			System.out.println("fast");
		}
		
		
		
	}

}
