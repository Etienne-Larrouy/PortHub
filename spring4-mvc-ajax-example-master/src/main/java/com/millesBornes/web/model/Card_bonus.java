package com.millesBornes.web.model;


public class Card_bonus extends Card {
	
	private String bonus;
	private Player player;

	public Card_bonus(String name,String bonus) {
		super(name);
		// TODO Auto-generated constructor stub
		this.bonus = bonus;
	}
	
	public void for_player(Player player){
		this.player = player;
	}
	
	public void Action(){
		if(bonus.equals("pickpocket")){
			System.out.println("pickpocket");
		}
		if(bonus.equals("bouffe")){
			System.out.println("bouffe");
		}
		if(bonus.equals("cofee")){
			System.out.println("coffee");
		}
		if(bonus.equals("car")){
			System.out.println("pickpoccarket");
		}
		
		
		
	}

}
