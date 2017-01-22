package com.millesBornes.web.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class Part {
	
	//Attributes
	@JsonView(Views.Public.class)
	private ArrayList <Player> list_Player;
	private ArrayList<Card> pack_card;
	private String IP;
	private int nb_player;
	public double getDepartLatitude() {
		return departLatitude;
	}

	public void setDepartLatitude(double departLatitude) {
		this.departLatitude = departLatitude;
	}

	public double getDepartLongitude() {
		return departLongitude;
	}

	public void setDepartLongitude(double departLongitude) {
		this.departLongitude = departLongitude;
	}

	@JsonView(Views.Public.class)
	private double departLatitude= 48.00611;
	@JsonView(Views.Public.class)
	private double departLongitude= 0.199556;
	public int nbp;

	public Part(String IP,int nb_player){
		nbp = 0;
		this.IP = IP;
		this.setNb_player(nb_player);
		this.list_Player = new ArrayList<>(nb_player);
		init_pack_card();
	}
	
	public void add_player(Player player){
		this.list_Player.add(player);
	}

	public ArrayList <Player> getList_Player() {
		return list_Player;
	}

	public void setList_Player(ArrayList <Player> list_Player) {
		this.list_Player = list_Player;
	}

	public String getIP() {
		return IP;
	}
	
	public void init_pack_card(){
		this.pack_card = new ArrayList<>(85);
		
		
		
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("25kms", 25,"25.jpg"));
		}
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("50kms", 50,"50.jpg"));
		}
		for(int i=0;i<10;i++){
			this.pack_card.add(new Card_distance("75kms", 75,"75.jpg"));
		}
		for(int i=0;i<12;i++){
			this.pack_card.add(new Card_distance("100kms", 100,"100.jpg"));
		}
		for(int i=0;i<4;i++){
			this.pack_card.add(new Card_distance("200kms", 200,"200.jpg"));
		}
		//46 cartes
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "accidente","null"));
		}
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "snow","null"));
		}
		for(int i=0;i<5;i++){
			this.pack_card.add(new Card_Malus("malus", "traffic","null"));
		}
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_Malus("malus", "manif","null"));
		}
		for(int i=0;i<4;i++){
			this.pack_card.add(new Card_Malus("malus", "fast","null"));
		}
		//64 cartes
		for(int i=0;i<3;i++){
			this.pack_card.add(new Card_bonus("bonus", "pickpocket","null"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "coffee","null"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "bouffe","null"));
		}
		for(int i=0;i<6;i++){
			this.pack_card.add(new Card_bonus("bonus", "car","null"));
		}
		//85	
		System.out.println("pacquet initialis�");
	}

	public Card give_card(){
		int nb = (int) (Math.random()*(pack_card.size()-1));
		Card card = this.pack_card.get(nb);
		this.pack_card.remove(nb);
		return card;

	}
	
	public int nb_card(){
		
		
		return pack_card.size();
	}
	
	public ArrayList<Card> getList_card() {
		return pack_card;
	}
	
	public Card get_card(int num_card){
		return pack_card.get(num_card);
	}

	public void setList_card(ArrayList<Card> list_card) {
		this.pack_card = list_card;
	}

	public int getNb_player() {
		return nb_player;
	}

	public void setNb_player(int nb_player) {
		this.nb_player = nb_player;
	}

	public ArrayList<Card> getPack_card() {
		return pack_card;
	}

	public void setPack_card(ArrayList<Card> pack_card) {
		this.pack_card = pack_card;
	}
	
	
	

}
