package com.millesBornes.web.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.controller.ObjectUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class Player {

	//Attributes
	@JsonView(Views.Public.class)
	private String pseudo;
	private double distance;
	private int hunger;
	private int sleep;
	private ArrayList<Card> list_card_player;

	@JsonView(Views.Public.class)
	private boolean state;
	
	@FXML
	private Button card1;
	@FXML
	private Button card2;
	@FXML
	private Button card3;
	@FXML
	private Button card4;
	@FXML
	private Button card5;
	@FXML
	private Button card6;
	@FXML
	private Button card7;
	
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
		//display_list_card();
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


	public double getDistance() {
		return distance;
	}


	public void setDistance(double d) {
		this.distance += d;
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
	
	public static void main(String argv[]) throws Exception
	{
		
		
		Player p = new Player("p1");
		
		 String clientSentence;
	       

	       Socket clientSocket = new Socket("localhost", 3456);
			
	       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	       clientSentence = inFromServer.readLine();
	       p.setList_card((ArrayList<Card>)ObjectUtil.fromString(clientSentence));

			
	       p.display_list_card();
	       
	}
}
