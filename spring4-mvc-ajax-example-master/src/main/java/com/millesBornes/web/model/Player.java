package com.millesBornes.web.model;

import java.util.ArrayList;

public class Player {

	//Attributes
	private String pseudo;
	private int distance;
	private int hunger;
	private int sleep;
	private ArrayList<Card> list_card_player;
	private boolean state;
	
	public Player(String pseudo){
		this.pseudo = pseudo;
		this.distance = 0;
		this.hunger = 10;
		this.sleep = 10;
		this.list_card_player = new ArrayList<>();
		this.state = false;
	}
	
	public int nb_card(){
		return list_card_player.size();
	}
	
	public void use_card(int nb_card){
		
		System.out.println("size list "+this.list_card_player.size());
		
		//Action
		System.out.println("action : "+list_card_player.get(nb_card).getName());
		//delelete cards
		
		//this.list_card_player.set(nb_card, null);
		this.list_card_player.get(nb_card).setEtat(false);
		display_list_card();
	}
	
	
	public void add_card(Card card){
		if(this.list_card_player.size()<7){
			
			this.list_card_player.add(card);
		}else{
			for(int i=0;i<this.list_card_player.size();i++){
				if(this.list_card_player.get(i).isEtat()){
					this.list_card_player.add(card);
					
				}
			}
		}
		
		
		
		
	}
	
	public void display_list_card(){
		for(int i =0; i<list_card_player.size();i++){
			System.out.print(list_card_player.get(i).getName());
				if(!list_card_player.get(i).isEtat()){
					System.out.println("carte utilis�e.");
				}else{
					System.out.println("");
				}
				
		}
	}

	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public int getHunger() {
		return hunger;
	}


	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public ArrayList<Card> getList_card() {
		return this.list_card_player;
	}

	public void setList_card(ArrayList<Card> list_card) {
		this.list_card_player = list_card;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
}
